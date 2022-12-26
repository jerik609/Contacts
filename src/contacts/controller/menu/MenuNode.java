package contacts.controller.menu;

import contacts.common.tree.Node;
import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class MenuNode extends NavigatingCommandNode {
    private final Scanner scanner;

    protected MenuNode(
            String key,
            NavigatingCommandNode parent,
            CommandExecutor executor,
            Supplier<List<Command>> commands,
            Scanner scanner) {
        super(key, parent, executor, commands);
        this.scanner = scanner;
    }

    @Override
    protected NavigatingCommandNode navigate() {
        String sb = "[" + (this.getValue().equals("[number]") ? "record" : this.getValue())  + "]" +
                " Enter action " +
                "(" +
                this.getAllChildren().values().stream()
                        .map(Node::getKey)
                        .reduce((str1, str2) -> str1 + ", " + str2).orElse("empty menu!") +
                "): ";
        System.out.print(sb);

        var selection = scanner.nextLine();
        var selectionOrig = selection;

        // special handling for number selections
        if (this.getValue().equals("[number]")) {
            if (selection.equals("[number]")) { // can't select [number], default to ""
                selection = "";
            }
        } else if (selection.matches("[0-9]*")) { // selection is a number
            selection = "[number]"; // and make it pass
        }

        //TODO: can we get rid of this cast somehow? (but it should be generally safe)
        return (NavigatingCommandNode) this
                .getChildByKey(selection.trim().toLowerCase())
                .map(item -> {
                    if (item instanceof SelectionAwareMenuNode node) {
                        node.acceptSelection(Integer.parseInt(selectionOrig) - 1);
                    }
                    return item;
                })
                .orElseGet(() -> {
                    System.out.println("no such option!");
                    return this;
                });
    }
}

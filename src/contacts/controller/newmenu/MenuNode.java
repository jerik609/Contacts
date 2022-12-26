package contacts.controller.newmenu;

import contacts.common.tree.NavigatingCommandNode;
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
        String sb = "[" + this.getValue() + "]" +
                " Enter action " +
                "(" +
                this.getAllChildren().values().stream()
                        .map(Node::getKey)
                        .reduce((str1, str2) -> str1 + ", " + str2).orElse("empty menu!") +
                "): ";
        System.out.print(sb);

        var selection = scanner.nextLine();
        //TODO: can we get rid of this cast somehow? (but it should be generally safe)
        return (NavigatingCommandNode) this
                .getChildByKey(selection.trim().toLowerCase())
                .orElseGet(() -> {
                    System.out.println("no such option!");
                    return this;
                });
    }
}

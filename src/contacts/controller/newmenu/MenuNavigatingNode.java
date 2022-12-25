package contacts.controller.newmenu;

import contacts.common.tree.NavigatingNode;
import contacts.common.tree.Node;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class MenuNavigatingNode extends NavigatingNode {
    private final Scanner scanner;

    protected MenuNavigatingNode(
            Scanner scanner,
            String key,
            Supplier<NavigatingNode> actionSupplier,
            Node<Supplier<? extends NavigatingNode>> parent,
            Map<String, Node<Supplier<? extends NavigatingNode>>> children) {
        super(key, actionSupplier, parent, children);
        this.scanner = scanner;
    }

    @Override
    protected NavigatingNode navigate() {
        if (this.isLeaf()) {
            return this.getValue().get();
        } else {
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
            return (NavigatingNode) this
                    .getChildByKey(selection.trim().toLowerCase())
                    .orElseGet(() -> {
                        System.out.println("no such option!");
                        return this;
                    });
        }
    }
}

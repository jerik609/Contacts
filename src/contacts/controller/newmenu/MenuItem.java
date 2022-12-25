package contacts.controller.newmenu;

import contacts.common.tree.Node;

import java.util.Map;
import java.util.Scanner;

public class MenuItem extends BaseItem {
    public MenuItem(String value, Node<String> parent, Map<String, Node<String>> children, Scanner scanner) {
        super(value, parent, children, scanner);
    }

    public MenuItem(String value, Node<String> parent, Scanner scanner) {
        super(value, parent, scanner);
    }

    @Override
    BaseItem executeItem() {
        String sb = "[" + this.getValue() + "]" +
                " Enter action " +
                "(" +
                this.getAllChildren().values().stream()
                        .map(Node::getValue)
                        .reduce((str1, str2) -> str1 + ", " + str2).orElse("empty menu!") +
                "): ";
        System.out.print(sb);
        var selection = scanner.nextLine();
        //TODO: has to cast, but it should be safe, but surely we can do it in a better way (recurring pattern finally?)
        return (BaseItem) this.getChildByKey(selection.trim().toLowerCase()).orElseGet(() -> {
                    System.out.println("no such option!");
                    return this;
                });
    }
}

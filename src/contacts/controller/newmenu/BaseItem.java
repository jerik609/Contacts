package contacts.controller.newmenu;

import contacts.common.tree.Node;

import java.util.Map;
import java.util.Scanner;

public abstract class BaseItem extends Node<String> {
    protected final Scanner scanner;

    public BaseItem(String value, Node<String> parent, Map<String, Node<String>> children, Scanner scanner) {
        super(value, parent, children);
        this.scanner = scanner;
    }

    public BaseItem(String value, Node<String> parent, Scanner scanner) {
        super(value, parent);
        this.scanner = scanner;
    }

    abstract BaseItem executeItem();
}

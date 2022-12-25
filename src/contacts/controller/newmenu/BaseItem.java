package contacts.controller.newmenu;

import contacts.common.tree.Node;

import java.util.Map;
import java.util.Scanner;

public abstract class BaseItem extends Node<BaseItem> {
    protected BaseItem(String key, BaseItem value, Node<BaseItem> parent, Map<String, Node<BaseItem>> children) {
        super(key, value, parent, children);
    }

    protected BaseItem(String key, BaseItem value, Node<BaseItem> parent) {
        super(key, value, parent);
    }

    abstract BaseItem executeItem();
}

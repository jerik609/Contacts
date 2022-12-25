package contacts.common.tree;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Tree implementation
 * Can not remove root node
 * Can only remove leaves
 * => can remove nodes, when all leaves have been removed
 * Can always change the carried value
 * @param <T>
 */
public class Node<T> {
    private final Node<T> parent;
    private final LinkedHashMap<String, Node<T>> children = new LinkedHashMap<>();
    private T value;

    public static <T> Node<T> createRootNode(T value, Map<String, Node<T>> children) {
        return new Node<>(value, null, children);
    }

    public static <T> Node<T> createRootNode(T value) {
        return new Node<>(value, null, Collections.emptyMap());
    }

    public static <T> Node<T> createInternalNode(T value, Node<T> parent, Map<String, Node<T>> children) {
        if (parent == null) {
            throw new InvalidParameterException("Node must not have a null parent");
        }
        return new Node<>(value, parent, children);
    }

    public Node(T value, Node<T> parent, Map<String, Node<T>> children) {
        this.parent = parent;
        this.children.putAll(children);
    }

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Optional<Node<T>> getParent() {
        return Optional.ofNullable(parent);
    }

    public LinkedHashMap<String, Node<T>> getAllChildren() {
        return children;
    }

    public Optional<Node<T>> getChildByKey(String key) {
        return Optional.ofNullable(children.get(key));
    }

    public boolean removeLeaf(String key) {
        var node = children.get(key);
        if (node == null) {
            System.out.println("no child with key = " + key);
            return false;
        } else {
            if (!node.isLeaf()) {
                System.out.println("node is not a leaf, not removed");
                return false;
            } else {
                children.remove(key);
                return true;
            }
        }
    }

    public boolean addChild(String key, Node<T> node) {
        if (children.containsKey(key)) {
            System.out.println("child exists with key = " + key);
            return false;
        } else {
            children.put(key, node);
            return true;
        }
    }
}

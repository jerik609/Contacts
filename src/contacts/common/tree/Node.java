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
    private final String key; // a unique identifier of the node
    private T value; // the functional value we actually store in the tree (can be anything)

    public static <T> Node<T> createRootNode(String key, T value, Map<String, Node<T>> children) {
        return new Node<>(key, value, null, children);
    }

    public static <T> Node<T> createRootNode(String key, T value) {
        return new Node<>(key, value, null, Collections.emptyMap());
    }

    public static <T> Node<T> createInternalNode(String key, T value, Node<T> parent) {
        if (parent == null) {
            throw new InvalidParameterException("Node must not have a null parent");
        }
        var node = new Node<>(key, value, parent);
        parent.addChild(node);
        return node;
    }

    protected Node(String key, T value, Node<T> parent, Map<String, Node<T>> children) {
        this.key = key;
        this.value = value;
        this.parent = parent;
        this.children.putAll(children);
        // check if parent has such child (if we're not root)
        if (parent != null) {
            if (parent.getChildByKey(key).isPresent()) {
                throw new InvalidParameterException("parent already has child with key: " + key);
            }
            // add as child of our parent
            parent.addChild(this);
        }
    }

    protected Node(String key, T value, Node<T> parent) {
        this(key, value, parent, Collections.emptyMap());
    }

    public boolean isRoot() {
        return parent != null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public String getKey() {
        return key;
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

    public boolean addChild(Node<T> node) {
        if (children.containsKey(key)) {
            System.out.println("child exists with key = " + key);
            return false;
        } else {
            children.put(node.key, node);
            return true;
        }
    }
}

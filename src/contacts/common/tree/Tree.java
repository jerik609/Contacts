package contacts.common.tree;

import java.security.InvalidParameterException;

public class Tree<T> {
    private final Node<T> root;

    public Tree(Node<T> rootNode) {
        if (rootNode.isRoot()) {
            throw new InvalidParameterException("the provided root node is not a root");
        }
        root = rootNode;
    }

    public Node<T> getRoot() {
        return root;
    }
}

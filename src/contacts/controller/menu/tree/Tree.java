package contacts.controller.menu.tree;

import java.util.Collections;

public class Tree<T> {
    private final Node<T> root;


    public Tree(T valueOfRoot) {
        root = Node.createRootNode(valueOfRoot, Collections.emptyMap());
    }

    public Node<T> getRoot() {
        return root;
    }
}

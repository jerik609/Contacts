package contacts.common.tree;

import java.util.Map;
import java.util.function.Supplier;

public abstract class NavigatingNode extends Node<Supplier<? extends NavigatingNode>> {
    protected NavigatingNode(
            String key,
            Supplier<? extends NavigatingNode> value,
            Node<Supplier<? extends NavigatingNode>> parent,
            Map<String, Node<Supplier<? extends NavigatingNode>>> children) {
        super(key, value, parent, children);
    }

    protected abstract NavigatingNode navigate();
}

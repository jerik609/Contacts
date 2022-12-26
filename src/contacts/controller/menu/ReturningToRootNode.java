package contacts.controller.menu;

import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.function.Supplier;

public class ReturningToRootNode extends NavigatingCommandNode {
    protected ReturningToRootNode(String key, NavigatingCommandNode parent, CommandExecutor executor, Supplier<List<Command>> commands) {
        super(key, parent, executor, commands);
    }

    @Override
    protected NavigatingCommandNode navigate() {
        NavigatingCommandNode node = this;
        while (node.isRoot()) {
            node = (NavigatingCommandNode) node.getParent().orElse(node);
        }
        return node;
    }
}

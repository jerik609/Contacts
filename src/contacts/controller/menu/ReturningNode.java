package contacts.controller.menu;

import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.function.Supplier;

public class ReturningNode extends NavigatingCommandNode {
    protected ReturningNode(
            String key,
            NavigatingCommandNode parent,
            CommandExecutor executor,
            Supplier<List<Command>> commands) {
        super(key, parent, executor, commands);
    }

    @Override
    protected NavigatingCommandNode navigate() {
        return (NavigatingCommandNode) this.getParent().orElseThrow(
                () -> new RuntimeException("no parent node, this indicates major inconsistency in menu setup")
        );
    }
}

package contacts.controller.newmenu;

import contacts.common.tree.NavigatingCommandNode;
import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.function.Supplier;

public class ReturningNavigatingNode extends NavigatingCommandNode {
    protected ReturningNavigatingNode(
            String key,
            NavigatingCommandNode parent,
            CommandExecutor executor,
            Supplier<List<Command>> commands) {
        super(key, parent, executor, commands);
    }

    @Override
    protected NavigatingCommandNode navigate() {
        return (NavigatingCommandNode) this.getParent().orElseThrow();
    }
}

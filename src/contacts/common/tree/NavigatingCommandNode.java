package contacts.common.tree;

import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.function.Supplier;

public abstract class NavigatingCommandNode extends Node<Supplier<List<Command>>> {
    private final CommandExecutor executor;

    protected NavigatingCommandNode(
            String key,
            NavigatingCommandNode parent,
            CommandExecutor executor,
            Supplier<List<Command>> value) {
        super(key, value, parent);
        this.executor = executor;
    }

    protected abstract NavigatingCommandNode navigate();

    public final NavigatingCommandNode execute() {
        this.getValue().get().forEach(executor::acceptCommand);
        executor.executeCommands();
        return navigate();
    }
}

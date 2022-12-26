package contacts.controller.menu;

import contacts.common.tree.Node;
import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class NavigatingCommandNode extends Node<String> {
    private final CommandExecutor executor;
    private final Supplier<List<Command>> commands;

    protected NavigatingCommandNode(
            String key,
            NavigatingCommandNode parent,
            CommandExecutor executor,
            Supplier<List<Command>> commands) {
        super(key, key, parent);
        this.executor = executor;
        this.commands = commands;
    }

    protected abstract NavigatingCommandNode navigate();

    protected void decorateCommand(Command command) {
        // noop in default implementation
    }

    public final NavigatingCommandNode execute() {
        this.commands.get().forEach(command -> {
            decorateCommand(command);
            executor.acceptCommand(command);
        });
        executor.executeCommands();
        return navigate();
    }
}

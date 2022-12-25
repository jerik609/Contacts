package contacts.controller.newmenu;

import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;
import contacts.common.tree.Node;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * ActionItem never has children!
 */
public class ActionItem extends BaseItem {
    private final CommandExecutor commandExecutor;
    private final Supplier<List<Command>> commandSupplier;

    public ActionItem(
            String value,
            Node<String> parent,
            Scanner scanner,
            CommandExecutor commandExecutor,
            Supplier<Command> commandSupplier) {
        super(value, parent, scanner);
        this.commandExecutor = commandExecutor;
        this.commandSupplier = commandSupplier;
    }

    @Override
    BaseItem executeItem() {
        for (var command : commandSupplier.get()) {
            commandExecutor.acceptCommand(command);
            commandExecutor.executeCommands();
        //TODO yet another cast, we can do better via polymorphism
        return (BaseItem) this.getParent().orElseThrow(() -> new RuntimeException("action node has no parent!"));
    }
}

package contacts.controller.newmenu;

import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;
import contacts.common.tree.Node;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ActionItem never has children!
 */
public class ActionItem extends BaseItem {
    private final CommandExecutor commandExecutor;
    private final Supplier<List<Command>> commandSupplier;

    protected ActionItem(String key, BaseItem value, Node<BaseItem> parent, Map<String, Node<BaseItem>> children, CommandExecutor commandExecutor, Supplier<List<Command>> commandSupplier) {
        super(key, value, parent, children);
        this.commandExecutor = commandExecutor;
        this.commandSupplier = commandSupplier;
    }

    protected ActionItem(String key, BaseItem value, Node<BaseItem> parent, CommandExecutor commandExecutor, Supplier<List<Command>> commandSupplier) {
        super(key, value, parent);
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
        return null;
    }
}

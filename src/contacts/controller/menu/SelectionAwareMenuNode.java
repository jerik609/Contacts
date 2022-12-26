package contacts.controller.menu;

import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.controller.command.CommandExecutor;
import contacts.controller.command.commands.SelectItemCommand;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class SelectionAwareMenuNode extends MenuNode implements SelectionAware {
    private int selection = -1;

    protected SelectionAwareMenuNode(String key, NavigatingCommandNode parent, CommandExecutor executor, Supplier<List<Command>> commands, Scanner scanner) {
        super(key, parent, executor, commands, scanner);
    }

    @Override
    public void acceptSelection(int selection) {
        this.selection = selection;
    }

    @Override
    protected void decorateCommand(Command command) {
        if (command instanceof SelectItemCommand selectItemCommand) {
            selectItemCommand.setSelection(selection);
        }
    }
}

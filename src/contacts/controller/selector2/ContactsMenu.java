package contacts.controller.selector2;

import contacts.controller.Controller;
import contacts.controller.command.Command;
import contacts.controller.command.commands.NoopCommand;
import contacts.controller.command.commands.StopCommand;
import contacts.controller.selector.enums.EntityAction;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ContactsMenu implements Supplier<Command> {
    private final Scanner scanner;

    private final Map<EntityAction, Supplier<Command>> menu = new HashMap<>();

    public ContactsMenu(Controller controller, Scanner scanner) {
        this.scanner = scanner;
        // TODO: reuse the ContactItemMenu
        menu.put(EntityAction.ADD, new ContactItemMenu(scanner, EntityAction.ADD));
        menu.put(EntityAction.REMOVE, () -> new NoopCommand("ContactsMenu -> REMOVE"));
        menu.put(EntityAction.EDIT, () -> new NoopCommand("ContactsMenu -> EDIT"));
        menu.put(EntityAction.COUNT, () -> new NoopCommand("ContactsMenu -> COUNT"));
        menu.put(EntityAction.INFO, () -> new NoopCommand("ContactsMenu -> INFO"));
        menu.put(EntityAction.EXIT, () -> new StopCommand(controller));
    }

    @Override
    public Command get() {
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        final var actionStr = scanner.nextLine();
        final var action = EntityAction.translateFrom(actionStr);
        if (menu.containsKey(action)) {
            return menu.get(action).get();
        } else {
            throw new InvalidParameterException("Unknown menu item: " + actionStr);
        }
    }
}

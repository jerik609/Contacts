package contacts.controller.oldmenu;

import contacts.controller.Contacts;
import contacts.controller.Controller;
import contacts.controller.command.Command;
import contacts.controller.command.commands.*;
import contacts.controller.oldmenu.enums.EntityAction;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ContactsMenu implements Supplier<Command> {
    private final Scanner scanner;

    private final Map<EntityAction, Supplier<Command>> menu = new HashMap<>();

    public ContactsMenu(Controller controller, Scanner scanner, Contacts contacts) {
        this.scanner = scanner;
        // TODO: reuse the ContactItemMenu
        menu.put(EntityAction.ADD, new ContactItemMenu(scanner, contacts, EntityAction.ADD));
        menu.put(EntityAction.REMOVE, () -> new ContactsRemoveCommand(scanner, contacts));
        menu.put(EntityAction.EDIT, () -> new ContactsEditCommand(scanner, contacts));
        menu.put(EntityAction.COUNT, () -> new ContactsCountCommand(contacts));
        menu.put(EntityAction.INFO, () -> new ContactsInfoCommand(contacts));
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

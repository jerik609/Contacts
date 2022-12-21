package contacts.controller.menu;

import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.controller.command.commands.OrganizationAddCommand;
import contacts.controller.command.commands.PersonAddCommand;
import contacts.controller.menu.enums.EntityAction;
import contacts.controller.menu.enums.EntityType;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ContactItemMenu implements Supplier<Command> {
    private final Scanner scanner;

    private final Map<EntityType, Supplier<Command>> menu = new HashMap<>();

    public ContactItemMenu(Scanner scanner, Contacts contacts, EntityAction action) {
        this.scanner = scanner;
        menu.put(EntityType.PERSON, () -> new PersonAddCommand(contacts, scanner));
        menu.put(EntityType.ORGANIZATION, () -> new OrganizationAddCommand(contacts, scanner));
    }

    @Override
    public Command get() {
        System.out.print("Enter the type (person, organization): ");
        final var typeStr = scanner.nextLine();
        final var type = EntityType.translateFrom(typeStr);
        if (menu.containsKey(type)) {
            return menu.get(type).get();
        } else {
            throw new InvalidParameterException("Unknown menu item: " + type);
        }
    }
}

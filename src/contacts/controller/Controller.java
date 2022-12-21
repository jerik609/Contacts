package contacts.controller;

import contacts.controller.command.CommandExecutor;
import contacts.controller.selector.*;
import contacts.controller.selector.enums.EntityAction;
import contacts.controller.selector.enums.EntityType;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);

    private boolean stop = false;

    private final Contacts contacts;
    private final TypeSelector typeSelector;
    private final CommandExecutor commandExecutor = new CommandExecutor();

    public void stop() {
        stop = true;
    }

    public Controller(Contacts contacts) {
        this.contacts = contacts;
        this.typeSelector = new TypeSelector(scanner, contacts, this);
    }

    public void run() {
        do {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            final var actionStr = scanner.nextLine();
            final var action = EntityAction.translateFrom(actionStr);

            System.out.print("Enter the type (person, organization): ");
            final var typeStr = scanner.nextLine();
            final var type = EntityType.translateFrom(typeStr);

            var actionSelector = typeSelector.selectType(type);
            var command = actionSelector.selectAction(action);
            if (command != null) {
                commandExecutor.acceptCommand(command);
            }
            commandExecutor.executeCommands();
        } while (!stop);
    }
}

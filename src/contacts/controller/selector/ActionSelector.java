package contacts.controller.selector;


import contacts.controller.Controller;
import contacts.controller.command.Command;
import contacts.controller.command.commands.StopCommand;

import java.security.InvalidParameterException;
import java.util.Scanner;

public abstract class ActionSelector {
    final Scanner scanner;
    private final Controller controller;

    ActionSelector(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public Command selectAction() {
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        final var actionStr = scanner.nextLine();
        final var action = Action.translateToMenuAction(actionStr);
        return switch (action) {
            case ADD -> addAction();
            case REMOVE -> removeAction();
            case EDIT -> editAction();
            case COUNT -> countAction();
            case INFO -> infoAction();
            case EXIT -> exitAction();
            case UNKNOWN -> throw new InvalidParameterException("Unknown action: " + actionStr);
        };
    }

    final Command exitAction() {
        System.out.println("Exiting...");
        return new StopCommand(controller);
    }

    abstract Command addAction();

    abstract Command removeAction();

    abstract Command editAction();

    abstract Command countAction();

    abstract Command infoAction();
}

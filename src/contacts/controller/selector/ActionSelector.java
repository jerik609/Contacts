package contacts.controller.selector;


import contacts.controller.command.Command;

import java.security.InvalidParameterException;
import java.util.Scanner;

public abstract class ActionSelector {
    final Scanner scanner;

    ActionSelector(Scanner scanner) {
        this.scanner = scanner;
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

    abstract Command addAction();

    abstract Command removeAction();

    abstract Command editAction();

    abstract Command countAction();

    abstract Command infoAction();

    abstract Command exitAction();
}

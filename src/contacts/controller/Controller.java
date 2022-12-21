package contacts.controller;

import contacts.controller.command.CommandExecutor;
import contacts.controller.selector.ActionSelector;
import contacts.controller.selector.PersonActionSelector;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);

    private boolean stop = false;

    private final Contacts contacts;
    private final ActionSelector actionSelector;
    private final CommandExecutor commandExecutor = new CommandExecutor();

    public void stop() {
        stop = true;
    }

    public Controller(Contacts contacts) {
        this.contacts = contacts;
        this.actionSelector = new PersonActionSelector(scanner, contacts, this);
    }

    public void run() {
        do {
            var command = actionSelector.selectAction();
            if (command != null) {
                commandExecutor.acceptCommand(command);
            }
            commandExecutor.executeCommands();
        } while (!stop);
    }
}

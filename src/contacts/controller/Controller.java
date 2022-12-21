package contacts.controller;

import contacts.controller.command.CommandExecutor;
import contacts.controller.selector.ContactsMenu;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);

    private boolean stop = false;

    private final Contacts contacts;
    private final CommandExecutor commandExecutor = new CommandExecutor();

    public void stop() {
        stop = true;
    }

    public Controller(Contacts contacts) {
        this.contacts = contacts;
    }

    public void run() {
        do {
            var menu = new ContactsMenu(this, scanner, contacts);
            var command = menu.get();
            if (command != null) {
                commandExecutor.acceptCommand(command);
            }
            commandExecutor.executeCommands();
        } while (!stop);
    }
}

package contacts.controller;

import contacts.controller.command.CommandExecutor;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner;

    private boolean stop = false;

    private final Contacts contacts;
    private final CommandExecutor commandExecutor = new CommandExecutor();

    public void stop() {
        stop = true;
    }

    public Controller(Scanner scanner, Contacts contacts) {
        this.scanner = scanner;
        this.contacts = contacts;
    }

    public void run2() {



    }

    public void run() {
//        do {
//            var menu = new ContactsMenu(this, scanner, contacts);
//            var command = menu.get();
//            if (command != null) {
//                commandExecutor.acceptCommand(command);
//            }
//            commandExecutor.executeCommands();
//        } while (!stop);
//        contacts.saveData();
    }
}

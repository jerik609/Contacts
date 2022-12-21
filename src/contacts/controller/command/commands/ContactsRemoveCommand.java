package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

import java.util.Scanner;

public class ContactsRemoveCommand implements Command {
    private final Scanner scanner;
    private final Contacts contacts;

    public ContactsRemoveCommand(Scanner scanner, Contacts contacts) {
        this.scanner = scanner;
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.removeEntry(scanner);
    }
}

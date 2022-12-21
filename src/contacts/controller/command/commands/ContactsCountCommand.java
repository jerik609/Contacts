package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class ContactsCountCommand implements Command {
    private final Contacts contacts;

    public ContactsCountCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.displayNumberOfEntries();
    }
}

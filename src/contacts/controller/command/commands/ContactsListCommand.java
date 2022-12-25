package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class ContactsListCommand implements Command {
    private final Contacts contacts;

    public ContactsListCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.listPhoneBook();
    }
}

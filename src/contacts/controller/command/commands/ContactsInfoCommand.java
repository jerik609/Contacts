package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class ContactsInfoCommand implements Command {
    private final Contacts contacts;

    public ContactsInfoCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.listPhoneBook();
    }
}

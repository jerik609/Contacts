package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class SearchCommand implements Command {
    private final Contacts contacts;

    public SearchCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.search();
    }
}

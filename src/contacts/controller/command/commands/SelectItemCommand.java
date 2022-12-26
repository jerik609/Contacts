package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class SelectItemCommand implements Command {
    private final Contacts contacts;

    public SelectItemCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        contacts.selectItem();
    }
}

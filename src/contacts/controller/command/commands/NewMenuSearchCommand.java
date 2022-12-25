package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class NewMenuSearchCommand implements Command {
    private final Contacts contacts;

    public NewMenuSearchCommand(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void execute() {
        


    }
}

package contacts.menu.command;

import contacts.data.entities.Person;
import contacts.input.MenuAction;
import contacts.menu.Contacts;
import contacts.menu.command.commands.AddPersonCommand;

import java.security.InvalidParameterException;

public class PersonCommandFactory {
    private final Contacts contacts;

    public PersonCommandFactory(Contacts contacts) {
        this.contacts = contacts;
    }

    public Command createCommand(MenuAction action, Person person) {
        return switch (action) {
            case ADD ->  new AddPersonCommand(contacts, person);



            default -> throw new InvalidParameterException("Unsupported command type: " + action);
        };
    }

}

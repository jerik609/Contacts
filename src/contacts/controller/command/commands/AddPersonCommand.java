package contacts.controller.command.commands;

import contacts.data.entities.Person;
import contacts.controller.Contacts;
import contacts.controller.command.Command;

public class AddPersonCommand implements Command {

    private final Contacts contacts;
    private final Person person;

    public AddPersonCommand(Contacts contacts, Person person) {
        this.contacts = contacts;
        this.person = person;
    }

    @Override
    public void execute() {
        contacts.addPerson(person);
    }

    @Override
    public String toString() {
        return "AddPersonCommand: " + person.getKey();
    }
}

package contacts.controller.command.commands;

import contacts.data.entities.Person;
import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.input.PersonReader;
import contacts.input.validators.NameValidator;

import java.util.Scanner;

public class PersonAddCommand implements Command {
    private final Contacts contacts;
    private final Person person;

    public PersonAddCommand(Contacts contacts, Scanner scanner) {
        this.contacts = contacts;
        person = new PersonReader(scanner).read(new Person.Builder(new NameValidator())).build();
    }

    @Override
    public void execute() {
        contacts.add(person);
    }
}

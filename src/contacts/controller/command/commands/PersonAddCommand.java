package contacts.controller.command.commands;

import contacts.data.entities.Person;
import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.input.ReadPerson;
import contacts.input.validators.NameValidator;

import java.util.Scanner;

public class PersonAddCommand implements Command {
    private final Scanner scanner;
    private final Contacts contacts;
    private final Person person;

    public PersonAddCommand(Contacts contacts, Scanner scanner) {
        this.contacts = contacts;
        this.scanner = scanner;
        person = new ReadPerson(scanner).readPerson(new Person.Builder(new NameValidator())).build();
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

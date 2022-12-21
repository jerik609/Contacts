package contacts.controller.selector;

import contacts.data.entities.Person;
import contacts.input.ReadPerson;
import contacts.controller.Contacts;
import contacts.controller.Controller;
import contacts.controller.command.Command;
import contacts.controller.command.commands.PersonAddCommand;
import contacts.input.validators.NameValidator;

import java.util.Scanner;

public class PersonActionSelector extends ActionSelector {
    private final Contacts contacts;

    private final ReadPerson input = new ReadPerson(scanner);
    private final Person.Builder personBuilder = new Person.Builder(new NameValidator());

    public PersonActionSelector(Scanner scanner, Contacts contacts, Controller menu) {
        super(scanner, menu);
        this.contacts = contacts;
    }

    @Override
    Command addAction() {
        System.out.println("Adding person...");
        Person person = input.readPerson(personBuilder).build();
        return new PersonAddCommand(contacts, person);
    }

    @Override
    Command removeAction() {
        contacts.removeEntry(scanner);
        return null;
    }

    @Override
    Command editAction() {
        contacts.editEntry(scanner);
        return null;
    }

    @Override
    Command countAction() {
        contacts.displayNumberOfEntries();
        return null;
    }

    @Override
    Command infoAction() {
        contacts.listPhoneBook();
        return null;
    }
}

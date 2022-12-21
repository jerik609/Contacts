package contacts.menu.selector;

import contacts.data.entities.Person;
import contacts.input.ReadPerson;
import contacts.menu.Contacts;
import contacts.menu.Controller;
import contacts.menu.command.Command;
import contacts.menu.command.CommandExecutor;
import contacts.menu.command.commands.AddPersonCommand;
import contacts.menu.command.commands.StopCommand;
import contacts.validators.NameValidator;

import java.util.Scanner;

public class PersonActionSelector extends ActionSelector {
    private final Contacts contacts;

    private final ReadPerson input = new ReadPerson(scanner);
    private final Person.Builder personBuilder = new Person.Builder(new NameValidator());
    private final Controller menu;


    public PersonActionSelector(Scanner scanner, Contacts contacts, Controller menu) {
        super(scanner);
        this.contacts = contacts;
        this.menu = menu;
    }

    @Override
    Command addAction() {
        System.out.println("Adding person...");
        Person person = input.readPerson(personBuilder).build();
        return new AddPersonCommand(contacts, person);
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

    @Override
    Command exitAction() {
        System.out.println("Exiting...");
        return new StopCommand(menu);
    }
}

package contacts.menu;

import contacts.data.entities.Person;
import contacts.input.MenuAction;
import contacts.input.ReadPerson;
import contacts.menu.command.*;
import contacts.validators.NameValidator;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ReadPerson input = new ReadPerson(scanner);
    private final Person.Builder personBuilder = new Person.Builder(new NameValidator());
    private final PersonCommandFactory commandFactory;
    private final CommandExecutor commandExecutor = new CommandExecutor();

    private final Contacts contacts;

    public Menu(Contacts contacts) {
        this.contacts = contacts;
        this.commandFactory = new PersonCommandFactory(contacts);
    }

    public void run() {
        var menuAction = MenuAction.EXIT;
        do {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            menuAction = MenuAction.translateToMenuAction(scanner.nextLine());

            switch (menuAction) {
                case COUNT -> contacts.displayNumberOfEntries();
                case EDIT -> contacts.editEntry(scanner);
                case REMOVE -> contacts.removeEntry(scanner);
                case ADD -> {
                    Person person = input.readPerson(personBuilder).build();
                    commandExecutor.acceptCommand(commandFactory.createCommand(menuAction, person));
                    commandExecutor.executeCommands();
                }
                case LIST -> contacts.listPhoneBook();
            }
        } while (menuAction != MenuAction.EXIT);
    }
}

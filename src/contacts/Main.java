package contacts;

import contacts.data.Person;
import contacts.input.Menu;
import contacts.input.MenuAction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final Menu input = new Menu(scanner);
        final Contacts contacts = new Contacts();

        final Person.PersonBuilder personBuilder = new Person.PersonBuilder();

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
                    contacts.addPerson(person);
                }
                case LIST -> contacts.listPhoneBook();
            }
        } while (menuAction != MenuAction.EXIT);
    }
}

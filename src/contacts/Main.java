package contacts;

import contacts.data.Person;
import contacts.input.ReadPerson;
import contacts.input.MenuAction;
import contacts.validators.NameValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final ReadPerson input = new ReadPerson(scanner);
        final Contacts contacts = new Contacts();

        final Person.Builder personBuilder = new Person.Builder(new NameValidator());

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

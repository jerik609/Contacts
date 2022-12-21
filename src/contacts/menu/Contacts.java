package contacts.menu;

import contacts.data.entities.Person;
import contacts.input.PersonAction;
import contacts.input.ReadPerson;
import contacts.pool.OldPool;
import contacts.validators.NameValidator;

import java.util.Scanner;

public class Contacts {

    private final OldPool<Person> phoneBook = new OldPool<>();

    public void addPerson(Person person) {
        phoneBook.insert(person);
        System.out.println("The record added.");
    }

    public void displayNumberOfEntries() {
        System.out.println("The phone Book has " + phoneBook.getSize() + " records.");
    }

    public void listPhoneBook() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to list!");
            return;
        }

        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
        }
    }

    public void removeEntry(Scanner scanner) {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
        }

        System.out.print("Select a record: ");
        final var selectionIndex = Integer.parseInt(scanner.nextLine());

        phoneBook.remove(entries.get(selectionIndex - 1).getKey());
        System.out.println("The record removed!");
    }

    public void editEntry(Scanner scanner) {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
        }

        System.out.print("Select a record: ");
        final var selectionIndex = Integer.parseInt(scanner.nextLine());
        System.out.print("Select a field (name, surname, number): ");
        final var selectionAttribute = PersonAction.translateToMenuAction(scanner.nextLine());

        final var builder = new Person.Builder(new NameValidator()).from(entries.get(selectionIndex - 1).getValue());
        phoneBook.insert(new ReadPerson(scanner).readPerson(builder, selectionAttribute).build());

        System.out.println("The record updated!");
    }
}

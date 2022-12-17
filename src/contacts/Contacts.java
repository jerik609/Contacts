package contacts;

import contacts.data.Person;
import contacts.data.PhoneNumber;
import contacts.pool.Pool;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Contacts {

    private final Pool<Person> phoneBook = new Pool<>();

    void addPerson(Person person) {
        phoneBook.insert(person);
        System.out.println("The record added.");
    }

    public void displayNumberOfEntries() {
        System.out.println("The phone Book has " + phoneBook.getSize() + " records.");
    }

    void listPhoneBook() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to list!");
            return;
        }

        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
        }
    }

    void removeEntry(Scanner scanner) {
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

    void editEntry(Scanner scanner) {
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
        final var selectionAttribute = scanner.nextLine();

        var builder = new Person.PersonBuilder(entries.get(selectionIndex - 1).getValue());

        switch (selectionAttribute) {
            case "name" -> {
                System.out.print("Enter number: ");
                phoneBook.insert(builder.firstname(scanner.nextLine()).build());
            }
            case "surname" -> {
                System.out.print("Enter surname: ");
                phoneBook.insert(builder.surname(scanner.nextLine()).build());
            }
            case "number" -> {
                System.out.print("Enter number: ");
                phoneBook.insert(builder.phoneNumber(PhoneNumber.buildPhoneNumber(scanner.nextLine())).build());
            }
            default -> throw new InvalidParameterException("Invalid parameter when editing record: " + selectionAttribute);
        }

        System.out.println("The record updated!");
    }

}

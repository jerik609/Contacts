package contacts.controller;

import contacts.data.entities.Organization;
import contacts.data.entities.Person;
import contacts.input.PersonAction;
import contacts.input.ReadPerson;
import contacts.input.validators.NameValidator;
import contacts.pool.Keyed;
import contacts.pool.PoolManager;

import java.util.Scanner;

public class Contacts {

    private final Scanner scanner;
    private final PoolManager phoneBook = new PoolManager();

    public Contacts(Scanner scanner) {
        this.scanner = scanner;
        phoneBook.addPool(Person.class);
        phoneBook.addPool(Organization.class);
    }

    public void add(Keyed item) {
        phoneBook.putValue(item);
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
            System.out.println((i + 1) + ". " + entries.get(i).shortDesc());
        }

        System.out.print("Select a record: ");
        final var selection = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println(entries.get(selection));
    }

    public void removeEntry(Scanner scanner) {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).shortDesc());
        }

        System.out.print("Select a record: ");
        final var selection = Integer.parseInt(scanner.nextLine()) - 1;
        final var entry = entries.get(selection);
        phoneBook.remove(entry);
        System.out.println("The record removed!");
    }

    public void editEntry(Scanner scanner) {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to edit!");
            return;
        }
        final var entries = phoneBook.getAll();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).shortDesc());
        }

        System.out.print("Select a record: ");
        final var selection = Integer.parseInt(scanner.nextLine()) - 1;
        final var updatedEntry = entries.get(selection).updateFromSelf(scanner);
        phoneBook.putValue(updatedEntry);
        System.out.println("The record updated!");
    }
}

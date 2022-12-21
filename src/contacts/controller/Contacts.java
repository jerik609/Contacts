package contacts.controller;

import contacts.data.entities.Organization;
import contacts.data.entities.Person;
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
        System.out.println("The record added.\n");
    }

    public void displayNumberOfEntries() {
        System.out.println("The phone Book has " + phoneBook.getSize() + " records.\n");
    }

    public void listPhoneBook() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to list!\n");
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
            System.out.println("No records to remove!\n");
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
        System.out.println("The record removed!\n");
    }

    public void editEntry(Scanner scanner) {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to edit!\n");
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
        System.out.println("The record updated!\n");
    }
}

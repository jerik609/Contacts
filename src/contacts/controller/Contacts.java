package contacts.controller;

import contacts.data.entities.Organization;
import contacts.data.entities.Person;
import contacts.pool.Keyed;
import contacts.pool.PoolManager;

import java.util.Scanner;

public class Contacts {

    private final PoolManager phoneBook = new PoolManager();

    public Contacts() {
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
            System.out.println((i + 1) + ". " + entries.get(i).toString());
        }
    }

    public void removeEntry(Scanner scanner) {
        System.out.println("not implemented");
//        if (phoneBook.getSize() == 0) {
//            System.out.println("No records to remove!");
//            return;
//        }
//
//        final var entries = phoneBook.getAll();
//        for (int i = 0; i < entries.size(); i++) {
//            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
//        }
//
//        System.out.print("Select a record: ");
//        final var selectionIndex = Integer.parseInt(scanner.nextLine());
//
//        phoneBook.remove(entries.get(selectionIndex - 1).getKey());
//        System.out.println("The record removed!");
    }

    public void editEntry(Scanner scanner) {
        System.out.println("not implemented");
//        if (phoneBook.getSize() == 0) {
//            System.out.println("No records to edit!");
//            return;
//        }
//
//        final var entries = phoneBook.getAll();
//        for (int i = 0; i < entries.size(); i++) {
//            System.out.println((i + 1) + ". " + entries.get(i).getValue().toString());
//        }
//
//        System.out.print("Select a record: ");
//        final var selectionIndex = Integer.parseInt(scanner.nextLine());
//        System.out.print("Select a field (name, surname, number): ");
//        final var selectionAttribute = PersonAction.translateToMenuAction(scanner.nextLine());
//
//        final var builder = new Person.Builder(new NameValidator()).from(entries.get(selectionIndex - 1).getValue());
//        phoneBook.insert(new ReadPerson(scanner).readPerson(builder, selectionAttribute).build());
//
//        System.out.println("The record updated!");
    }
}

package contacts.controller;

import contacts.data.entities.Organization;
import contacts.data.entities.Person;
import contacts.data.serialization.DataSaver;
import contacts.common.pool.Keyed;
import contacts.common.pool.PoolManager;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Contacts {

    private final Scanner scanner;
    private final PoolManager phoneBook = new PoolManager();
    private final DataSaver dataSaver = new DataSaver(phoneBook);
    private List<Keyed> lastListing = Collections.emptyList();
    private Keyed lastSelectedItem = null;

    public Contacts(Scanner scanner) {
        this.scanner = scanner;
        phoneBook.addPool(Person.class);
        phoneBook.addPool(Organization.class);
        loadData();
    }

    private void loadData() {
        try {
            dataSaver.loadData();
        } catch (IOException ex) {
            System.out.println("Failed to load data!");
        }
    }

    public void saveData() {
        try {
            dataSaver.saveData();
        } catch (IOException ex) {
            System.out.println("Failed to save data!");
        }
    }

    public void add(Keyed item) {
        phoneBook.putValue(item);
        System.out.println("The record added.");
    }

    public void displayNumberOfEntries() {
        System.out.println("The Phone Book has " + phoneBook.getSize() + " records.\n");
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
        System.out.println();

        // store listing in cache
        lastListing = entries;
    }

    public void selectItem(int selection) {
        if (selection == -1) {
            throw new InvalidParameterException("non initialized list command");
        }

        Keyed selectedItem;
        if (!lastListing.isEmpty()) { // normal op - after performing a selection
            selectedItem = lastListing.get(selection);
        } else { // after edit or delete
            lastListing = phoneBook.getAll();
            selectedItem = lastListing
                    .stream()
                    .filter(item -> item.getKey().equals(lastSelectedItem.getKey()))
                    .findFirst()
                    .orElse(null);
        }
        System.out.println(selectedItem == null ? "" : selectedItem);

        // store selection in cache
        lastSelectedItem = selectedItem;
    }

    public void removeEntry(Scanner scanner) {
        if (lastSelectedItem == null) {
            System.out.println("No records to remove!\n");
            return;
        }

        phoneBook.remove(lastSelectedItem);

        // invalidate cache
        lastListing = Collections.emptyList();

        System.out.println("The record removed!");
    }

    public void editEntry(Scanner scanner) {
        if (lastSelectedItem == null) {
            System.out.println("No records to edit!\n");
            return;
        }

        final var updatedEntry = lastSelectedItem.updateFromSelf(scanner);
        phoneBook.putValue(updatedEntry);

        // invalidate cache
        lastListing = Collections.emptyList();

        System.out.println("Saved");
    }

    public void search() {
        System.out.print("Enter search query: ");
        var searchString = scanner.nextLine().toLowerCase();

        final var entries = phoneBook.getAll()
                .stream()
                .filter(item -> item.searchableDesc().trim().toLowerCase().matches(".*" + searchString + ".*"))
                .collect(Collectors.toList());
        System.out.println("Found " + entries.size() + ((entries.size() == 1) ? " results" : " result"));

        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).shortDesc());
        }
        System.out.println();

        // store listing in cache
        lastListing = entries;
    }
}

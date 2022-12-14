package contacts.controller.menu;

import contacts.controller.Contacts;
import contacts.controller.command.CommandExecutor;
import contacts.controller.command.commands.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Contacts contacts;
    private final CommandExecutor executor = new CommandExecutor();
    private NavigatingCommandNode currentItem;
    private boolean stop = false;

    private void constructMenu(Scanner scanner) {
        var root = new MenuNode("menu", null, executor, Collections::emptyList, scanner);

        // ADD ================================================================
        {
            var add = new MenuNode("add", root, executor, Collections::emptyList, scanner);
            // PERSON
            var addPerson = new ReturningToRootNode("person", add, executor, () -> List.of(new PersonAddCommand(contacts, scanner)));
            // ORGANIZATION
            var addOrganization = new ReturningToRootNode("organization", add, executor, () -> List.of(new OrganizationAddCommand(contacts, scanner)));
        }

        // LIST ===============================================================
        {
            var list = new MenuNode("list", root, executor, () -> List.of(new ContactsListCommand(contacts)), scanner);
            // [number]
            {
                var listNumber = new SelectionAwareMenuNode("[number]", list, executor, () -> List.of(new SelectItemCommand(contacts)), scanner);
                // edit
                var listNumberEdit = new ReturningNode("edit", listNumber, executor, () -> List.of(new ContactsEditCommand(scanner, contacts)));
                // delete
                var listNumberDelete = new ReturningToRootNode("delete", listNumber, executor, () -> List.of(new ContactsRemoveCommand(scanner, contacts)));
                // menu
                var listNumberMenu = new ReturningToRootNode("menu", listNumber, executor, Collections::emptyList);
            }
            // BACK
            var listBack = new ReturningToRootNode("back", list, executor, Collections::emptyList);
        }

        // SEARCH =============================================================
        {
            var search = new MenuNode("search", root, executor, () -> List.of(new SearchCommand(contacts)), scanner);
            // [NUMBER]
            {
                var searchNumber = new SelectionAwareMenuNode("[number]", search, executor, () -> List.of(new SelectItemCommand(contacts)), scanner);
                // edit
                var searchNumberEdit = new ReturningNode("edit", searchNumber, executor, () -> List.of(new ContactsEditCommand(scanner, contacts)));
                // delete
                var searchNumberDelete = new ReturningToRootNode("delete", searchNumber, executor, () -> List.of(new ContactsRemoveCommand(scanner, contacts)));;
                // menu
                var searchNumberMenu = new ReturningToRootNode("menu", searchNumber, executor, Collections::emptyList);
            }
            // BACK
            var searchBack = new ReturningToRootNode("back", search, executor, Collections::emptyList);
            // AGAIN
            var searchAgain = new ReturningNode("again", search, executor, Collections::emptyList);
        }

        // COUNT ==============================================================
        {
            var itemCount = new ReturningNode("count", root, executor, () -> List.of(new ContactsCountCommand(contacts)));
        }

        // EXIT ===============================================================
        {
            var itemExit = new ReturningNode("exit", root, executor, () -> List.of(new StopCommand(this)));
        }

        currentItem = root;
    }

    public Menu(Scanner scanner, Contacts contacts) {
        this.contacts = contacts;
        constructMenu(scanner);
    }

    public void run() {
        while (!stop) {
            currentItem = currentItem.execute();
        }
        contacts.saveData();
    }

    public void stop() {
        stop = true;
    }
}

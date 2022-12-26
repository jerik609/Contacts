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
            var addPerson = new ReturningNode("person", add, executor, () -> List.of(new PersonAddCommand(contacts, scanner)));
            // ORGANIZATION
            var addOrganization = new ReturningNode("organization", add, executor, () -> List.of(new OrganizationAddCommand(contacts, scanner)));
        }

        // LIST ===============================================================
        {
            var list = new MenuNode("list", root, executor, () -> List.of(new ContactsListCommand(contacts)), scanner);
            // [number]
            {
                var listNumber = new MenuNode("[number]", list, executor, () -> List.of(new NoopCommand("number")), scanner);
                // edit
                var listNumberEdit = new ReturningNode("edit", listNumber, executor, () -> List.of(new NoopCommand("list number edit")));
                // delete
                var listNumberDelete = new ReturningNode("delete", listNumber, executor, () -> List.of(new NoopCommand("list number delete")));
                // menu
                var listNumberMenu = new ReturningToRootNode("menu", listNumber, executor, Collections::emptyList);
            }
            // BACK
            var listBack = new ReturningToRootNode("back", list, executor, () -> List.of(new NoopCommand("back")));
        }

        // SEARCH =============================================================
        {
            var search = new MenuNode("search", root, executor, () -> List.of(new NewMenuSearchCommand(contacts)), scanner);
            // [NUMBER]
            {
                var searchNumber = new MenuNode("[number]", search, executor, () -> List.of(new NoopCommand("number")), scanner);
                // edit
                var searchNumberEdit = new ReturningNode("edit", searchNumber, executor, () -> List.of(new NoopCommand("list number edit")));
                // delete
                var searchNumberDelete = new ReturningNode("delete", searchNumber, executor, () -> List.of(new NoopCommand("list number delete")));
                // menu
                var searchNumberMenu = new ReturningToRootNode("menu", searchNumber, executor, Collections::emptyList);
            }
            // BACK
            var searchBack = new ReturningToRootNode("back", search, executor, () -> List.of(new NoopCommand("back")));
            // AGAIN
            var searchAgain = new ReturningNode("again", search, executor, () -> List.of(new NoopCommand("again")));
        }

        // COUNT ==============================================================
        {
            var itemCount = new ReturningNode("count", root, executor, () -> List.of(new ContactsCountCommand(contacts)));
        }

        // EXIT ===============================================================
        {
            var itemExit = new ReturningNode("exit", root, executor, () -> List.of(new NewMenuStopCommand(this)));
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
        //contacts.saveData();
    }

    public void stop() {
        stop = true;
    }
}

package contacts.controller.newmenu;

import contacts.controller.Contacts;
import contacts.controller.command.CommandExecutor;
import contacts.controller.command.commands.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final Contacts contacts;
    private final CommandExecutor commandExecutor = new CommandExecutor();
    private BaseItem currentItem;
    private boolean stop = false;

    private void constructMenu(Scanner scanner) {
        var root = new MenuNavigatingNode("menu", null, commandExecutor, Collections::emptyList, scanner);

        var itemAdd = new MenuNavigatingNode("add", root, commandExecutor, Collections::emptyList, scanner);
        var itemList = new MenuNavigatingNode("list", root, commandExecutor, Collections::emptyList, scanner);
        var itemSearch = new MenuNavigatingNode("search", root, commandExecutor, Collections::emptyList, scanner);
        var itemCount = new MenuNavigatingNode("count", root, commandExecutor, Collections::emptyList, scanner);
        var itemExit = new ReturningNavigatingNode("exit", root, commandExecutor, () -> List.of(new NewMenuStopCommand(this)));








//                new MenuItem("add", root, scanner);
//        var itemList = new ActionItem("list", root, scanner, commandExecutor,
//                () -> List.of(new ContactsListCommand(contacts)));
//        var itemSearch = new ActionItem("search", root, scanner, commandExecutor,
//                () -> List.of(new ContactsListCommand(contacts), new NewMenuSearchCommand(contacts)));
//        var itemCount = new ActionItem("count", root, scanner, commandExecutor,
//                () -> List.of(new ContactsCountCommand(contacts)));
//        var itemExit = new ActionItem("exit", root, scanner, commandExecutor,
//                () -> List.of(new NewMenuStopCommand(this)));
//
//        root.addChild("add", itemAdd);
//
//        //TODO: we can merge new (with parent) and adding of the child to the parent (will make the tree more consistent)
//        var itemPerson = new ActionItem("person", itemAdd, scanner, commandExecutor,
//                () -> List.of(new PersonAddCommand(contacts, scanner)));
//        var itemOrganization = new ActionItem("organization", itemAdd, scanner, commandExecutor,
//                () -> List.of(new OrganizationAddCommand(contacts, scanner)));
//
//        itemAdd.addChild("person", itemPerson);
//        itemAdd.addChild("organization", itemOrganization);
//
//        root.addChild("list", itemList);
//        root.addChild("search", itemSearch);
//        root.addChild("count", itemCount);
//        root.addChild("exit", itemExit);
//
//        currentItem = root;
    }

    public Menu(Scanner scanner, Contacts contacts) {
        this.scanner = scanner;
        this.contacts = contacts;
        constructMenu(this.scanner);
    }

    public void run() {
        while (!stop) {
            currentItem = currentItem.executeItem();
        }
        contacts.saveData();
    }

    public void stop() {
        stop = true;
    }
}

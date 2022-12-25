package contacts.controller.menu;

import contacts.controller.Contacts;
import contacts.controller.command.CommandExecutor;
import contacts.controller.command.commands.ContactsCountCommand;
import contacts.controller.command.commands.NoopCommand;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final Contacts contacts;
    private final CommandExecutor commandExecutor = new CommandExecutor();
    private BaseItem currentItem;

    private void constructMenu(Scanner scanner) {
        var root = new MenuItem("menu", null, scanner);

        var itemAdd = new MenuItem("add", root, scanner);
        var itemList = new MenuItem("list", root, scanner);
        var itemSearch = new ActionItem("search", root, scanner, commandExecutor, () -> new NoopCommand("search"));
        var itemCount = new ActionItem("count", root, scanner, commandExecutor, () -> new ContactsCountCommand(contacts));
        var itemExit = new ActionItem("exit", root, scanner, commandExecutor, () -> new NoopCommand("exit"));

        root.addChild("add", itemAdd);

        //TODO: we can merge new (with parent) and adding of the child to the parent
        var itemPerson = new MenuItem("person", itemAdd, scanner);
        var itemOrganization = new MenuItem("organization", itemAdd, scanner);

        itemAdd.addChild("person", itemPerson);
        itemAdd.addChild("organization", itemOrganization);

        root.addChild("list", itemList);
        root.addChild("search", itemSearch);
        root.addChild("count", itemCount);
        root.addChild("exit", itemExit);

        currentItem = root;
    }

    public Menu(Scanner scanner, Contacts contacts) {
        this.scanner = scanner;
        this.contacts = contacts;
        constructMenu(this.scanner);
    }

    public void run() {
        while (true) {
            currentItem = currentItem.executeItem();
        }
    }

}

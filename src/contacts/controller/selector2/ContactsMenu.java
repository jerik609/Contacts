package contacts.controller.selector2;

import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.controller.selector.enums.EntityAction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ContactsMenu implements MenuProvider {

    private final Map<EntityAction, Supplier<Command>> menu = new HashMap<>();

    ContactsMenu() {
        menu.put(EntityAction.ADD, );
    }

    Command getCommand() {



        return null;
    }

}

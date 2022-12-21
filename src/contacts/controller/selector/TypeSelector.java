package contacts.controller.selector;

import contacts.controller.Contacts;
import contacts.controller.Controller;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class TypeSelector {
    private final Scanner scanner;
    private final Controller controller;
    private final Contacts contacts;

    public TypeSelector(Scanner scanner, Contacts contacts, Controller controller) {
        this.scanner = scanner;
        this.contacts = contacts;
        this.controller = controller;
    }

    public ActionSelector selectType(EntityType type) {
        return switch (type) {
            case PERSON -> new PersonActionSelector(scanner, contacts, controller);
            case ORGANIZATION -> new OrganizationActionSelector(scanner, controller);
            case UNKNOWN -> throw new InvalidParameterException("Unknown type: " + type.getOriginalValue());
        };
    }
}

package contacts.controller.selector;

import contacts.controller.Controller;
import contacts.controller.command.Command;

import java.util.Scanner;

public class OrganizationActionSelector extends ActionSelector {
    OrganizationActionSelector(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    Command addAction() {
        return null;
    }

    @Override
    Command removeAction() {
        return null;
    }

    @Override
    Command editAction() {
        return null;
    }

    @Override
    Command countAction() {
        return null;
    }

    @Override
    Command infoAction() {
        return null;
    }
}

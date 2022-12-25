package contacts;

import contacts.controller.Contacts;
import contacts.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var contacts = new Contacts(scanner);
        final var menu = new Controller(scanner, contacts);
        menu.run();
    }
}

package contacts;

import contacts.controller.Contacts;
import contacts.controller.Controller;
import contacts.controller.menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var contacts = new Contacts(scanner);
        var menu = new Menu(scanner, contacts);
        menu.run();
    }
}

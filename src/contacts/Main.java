package contacts;

import contacts.controller.Contacts;
import contacts.controller.Controller;

public class Main {
    public static void main(String[] args) {
        final Contacts contacts = new Contacts();
        final Controller menu = new Controller(contacts);
        menu.run();
    }
}

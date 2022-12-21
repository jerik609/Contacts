package contacts;

import contacts.menu.Contacts;
import contacts.menu.Controller;

public class Main {
    public static void main(String[] args) {
        final Contacts contacts = new Contacts();
        final Controller menu = new Controller(contacts);
        menu.run();
    }
}

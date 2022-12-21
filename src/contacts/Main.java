package contacts;

import contacts.menu.Contacts;
import contacts.menu.Menu;

public class Main {
    public static void main(String[] args) {
        final Contacts contacts = new Contacts();
        final Menu menu = new Menu(contacts);
        menu.run();
    }
}

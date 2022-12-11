package contacts;

import contacts.data.Person;
import contacts.data.Pool;

public class Contacts {

    private final Pool<Person> phoneBook = new Pool<>();

    void addPerson(Person person) {
        phoneBook.insert(person);
    }

    void displayPhoneBook() {
        System.out.println("--> Contents of the phone book:");
        for (var item : phoneBook.getAll()) {
            System.out.println(item.toString());
        }
        System.out.println("--> End.");
    }
}

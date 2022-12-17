package contacts.input;

import contacts.data.Person;
import contacts.data.PhoneNumber;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Person.PersonBuilder readPerson(Person.PersonBuilder builder) {
        System.out.print("Enter the name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        return builder.firstname(firstname).surname(surname).phoneNumber(PhoneNumber.buildPhoneNumber(phoneNumber));
    }

}

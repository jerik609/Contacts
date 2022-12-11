package contacts.input;

import contacts.data.Person;

import java.util.Scanner;

public class Input {

    private final Scanner scanner;
    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public Person.PersonBuilder readPerson(Person.PersonBuilder builder) {
        System.out.println("Enter the name of the person:");
        String firstname = scanner.next();

        System.out.println("Enter the surname of the person");
        String surname = scanner.next();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.next();

        return builder.firstname(firstname).surname(surname).phoneNumber(phoneNumber);
    }

}

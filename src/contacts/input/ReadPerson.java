package contacts.input;

import contacts.data.Person;
import contacts.data.PhoneNumber;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class ReadPerson {

    private final Scanner scanner;
    public ReadPerson(Scanner scanner) {
        this.scanner = scanner;
    }

    public Person.PersonBuilder readPerson(Person.PersonBuilder builder, PersonAction action) {
        switch (action) {
            case NAME -> {
                System.out.print("Enter name: ");
                builder.firstname(scanner.nextLine());
            }
            case SURNAME -> {
                System.out.print("Enter surname: ");
                builder.surname(scanner.nextLine());
            }
            case NUMBER -> {
                System.out.print("Enter number: ");
                builder.phoneNumber(PhoneNumber.buildPhoneNumber(scanner.nextLine()));
            }
            default -> throw new InvalidParameterException("Invalid parameter when editing record: " + action);
        }
        return builder;
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

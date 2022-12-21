package contacts.input;

import contacts.data.attributes.Gender;
import contacts.data.entities.Person;
import contacts.data.attributes.PhoneNumber;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class PersonReader {
    private final Scanner scanner;

    public PersonReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Person.Builder read(Person.Builder builder, PersonAction action) {
        switch (action) {
            case NAME -> {
                System.out.print("Enter name: ");
                builder.firstname(scanner.nextLine());
            }
            case SURNAME -> {
                System.out.print("Enter surname: ");
                builder.surname(scanner.nextLine());
            }
            case BIRTH -> {
                System.out.print("Enter birth: ");
                builder.birthDate(scanner.nextLine());
            }
            case GENDER -> {
                System.out.print("Enter gender (M, F): ");
                builder.gender(Gender.from(scanner.nextLine()));
            }
            case NUMBER -> {
                System.out.print("Enter number: ");
                builder.phoneNumber(PhoneNumber.buildPhoneNumber(scanner.nextLine()));
            }
            default -> throw new InvalidParameterException("Invalid parameter when editing record: " + action);
        }
        return builder;
    }

    public Person.Builder read(Person.Builder builder) {
        System.out.print("Enter the name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        String birthDate = scanner.nextLine();

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        return builder
                .firstname(firstname)
                .surname(surname)
                .birthDate(birthDate)
                .gender(Gender.from(gender))
                .phoneNumber(PhoneNumber.buildPhoneNumber(phoneNumber));
    }
}

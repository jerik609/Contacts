package contacts.input;

import contacts.data.attributes.Address;
import contacts.data.attributes.PhoneNumber;
import contacts.data.entities.Organization;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class OrganizationReader {
    private final Scanner scanner;

    public OrganizationReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Organization.Builder read(Organization.Builder builder, OrganizationAction action) {
        switch (action) {
            case NAME -> {
                System.out.print("Enter name: ");
                builder.name(scanner.nextLine());
            }
            case ADDRESS -> {
                System.out.print("Enter address: ");
                builder.address(Address.buildAddress(scanner.nextLine()));
            }
            case NUMBER -> {
                System.out.print("Enter number: ");
                builder.phoneNumber(PhoneNumber.buildPhoneNumber(scanner.nextLine()));
            }
            default -> throw new InvalidParameterException("Invalid parameter when editing record: " + action);
        }
        return builder;
    }

    public Organization.Builder read(Organization.Builder builder) {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        return builder
                .name(name)
                .address(Address.buildAddress(address))
                .phoneNumber(PhoneNumber.buildPhoneNumber(phoneNumber));
    }
}

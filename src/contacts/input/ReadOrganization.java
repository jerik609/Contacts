package contacts.input;

import contacts.data.attributes.Address;
import contacts.data.attributes.Gender;
import contacts.data.attributes.PhoneNumber;
import contacts.data.entities.Organization;
import contacts.data.entities.Person;

import java.util.Scanner;

public class ReadOrganization {
    private final Scanner scanner;

    public ReadOrganization(Scanner scanner) {
        this.scanner = scanner;
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

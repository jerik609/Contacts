package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.PhoneNumber;
import contacts.input.OrganizationAction;
import contacts.input.OrganizationReader;
import contacts.input.validators.NameValidator;
import contacts.input.validators.Validator;
import contacts.pool.Keyed;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Organization extends ContactDetails {
    private final String name;

    private Organization(String name, Address address, PhoneNumber phoneNumber) {
        super(address, phoneNumber);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organization name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Number: " + phoneNumber + "\n" +
                super.toString();
    }

    @Override
    public Keyed updateFromSelf(Scanner scanner) {
        var builder = new Organization.Builder(new NameValidator());

        System.out.print("Select a field (name, address, number): ");
        final var selection = OrganizationAction.translateToMenuAction(scanner.nextLine());

        var updatedEntry = new OrganizationReader(scanner).read(builder.from(this), selection).build();
        updatedEntry.createdTime = this.createdTime;
        updatedEntry.updatedTime = LocalDateTime.now();
        updatedEntry.copyKeyFrom(this);

        return updatedEntry;
    }

    @Override
    public String shortDesc() {
        return name;
    }

    public static class Builder {
        private final Validator nameValidator;

        private String name;
        private Address address;
        private PhoneNumber phoneNumber;

        public Builder(Validator nameValidator) {
            this.nameValidator = nameValidator;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder from(Organization other) {
            this.name = other.name;
            this.address = other.address;
            this.phoneNumber = other.phoneNumber; // we can do this, phoneNumber is immutable
            return this;
        }

        public Organization build() {
            if (nameValidator.validate(name)) {
                return new Organization(name, address, phoneNumber);
            } else {
                System.out.println("Invalid organization: " + this);
                return null;
            }
        }

    }
}

package contacts.data.entities;

import contacts.input.OrganizationAction;
import contacts.input.OrganizationReader;
import contacts.input.validators.DefaultValidator;
import contacts.input.validators.Validator;
import contacts.common.pool.Keyed;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Organization extends ContactDetails implements Serializable {
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
        var builder = new Organization.Builder(new DefaultValidator());

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

    @Override
    public String searchableDesc() {
        return name + " " +
                address.getAddress() + " " +
                phoneNumber.getPhoneNumber();
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
                System.out.println("Invalid organization: " + name);
                return null;
            }
        }
    }

    @Serial
    Object writeReplace() {
        return new OrganizationProxy(this);
    }

    public static class OrganizationProxy implements Serializable {
        private final String name;
        private final String address;
        private final String phoneNumber;

        public OrganizationProxy(Organization organization) {
            this.name = organization.name;
            this.address = organization.address.getAddress();
            this.phoneNumber = organization.phoneNumber.getPhoneNumber();
        }

        @Serial
        Object readResolve() {
            var builder = new Builder(new DefaultValidator())
                    .name(name)
                    .address(Address.buildAddress(address))
                    .phoneNumber(PhoneNumber.buildPhoneNumber(phoneNumber));
            return builder.build();
        }
    }
}

package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.Gender;
import contacts.data.attributes.PhoneNumber;
import contacts.input.PersonAction;
import contacts.input.PersonReader;
import contacts.input.validators.NameValidator;
import contacts.input.validators.Validator;
import contacts.common.pool.Keyed;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Person extends ContactDetails implements Serializable {
    private final String firstname;
    private final String surname;
    private final String birthDate;
    private final Gender gender;

    private Person(String firstname, String surname, String birthDate, Gender gender, Address address, PhoneNumber phoneNumber) {
        super(address, phoneNumber);
        this.firstname = firstname;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + firstname + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + (birthDate.length() == 0 ? "[no data]" : birthDate) + "\n" +
                "Gender: " + (gender == Gender.NON_BINARY ? "[no data]" : gender) + "\n" +
                "Number: " + phoneNumber + "\n" +
                super.toString();
    }

    @Override
    public Keyed updateFromSelf(Scanner scanner) {
        var builder = new Builder(new NameValidator());

        System.out.print("Select a field (name, surname, birth, gender, number): ");
        final var selection = PersonAction.translateToMenuAction(scanner.nextLine());

        var updatedEntry = new PersonReader(scanner).read(builder.from(this), selection).build();
        updatedEntry.createdTime = this.createdTime;
        updatedEntry.updatedTime = LocalDateTime.now();
        updatedEntry.copyKeyFrom(this);

        return updatedEntry;
    }

    @Override
    public String shortDesc() {
        return firstname + " " + surname;
    }

    public static class Builder {
        private final Validator nameValidator;

        private String firstname;
        private String surname;
        private String birthDate;
        private Gender gender;
        private Address address = Address.buildAddress("");
        private PhoneNumber phoneNumber;

        public Builder(Validator nameValidator) {
            this.nameValidator = nameValidator;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder phoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder from(Person other) {
            this.firstname = other.firstname;
            this.surname = other.surname;
            this.birthDate = other.birthDate;
            this.gender = other.gender;
            this.address = other.address;
            this.phoneNumber = other.phoneNumber; // we can do this, phone number is immutable
            return this;
        }

        public Person build() {
            if (nameValidator.validate(firstname) && nameValidator.validate(surname)) {
                return new Person(firstname, surname, birthDate, gender, address, phoneNumber);
            } else {
                System.out.println("Invalid person: " + this);
                return null;
            }
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "nameValidator=" + nameValidator +
                    ", firstname='" + firstname + '\'' +
                    ", surname='" + surname + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    ", gender=" + gender +
                    ", address=" + address +
                    ", phoneNumber=" + phoneNumber +
                    '}';
        }
    }

    @Serial
    Object writeReplace() {
        return new PersonProxy(this);
    }

    public static class PersonProxy implements Serializable {
        private final String firstname;
        private final String surname;
        private final String birthDate;
        private final String gender;
        private final String address;
        private final String phoneNumber;

        public PersonProxy(Person person) {
            this.firstname = person.firstname;
            this.surname = person.surname;
            this.birthDate = person.birthDate;
            this.gender = String.valueOf(person.gender.initValue);
            this.address = person.address.getAddress();
            this.phoneNumber = person.phoneNumber.getPhoneNumber();
        }

        @Serial
        Object readResolve() {
            var builder = new Builder(new NameValidator())
                    .firstname(firstname)
                    .surname(surname)
                    .birthDate(birthDate)
                    .gender(Gender.from(gender))
                    .phoneNumber(PhoneNumber.buildPhoneNumber(phoneNumber));
            return builder.build();
        }
    }
}

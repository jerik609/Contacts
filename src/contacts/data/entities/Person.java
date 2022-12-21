package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.Gender;
import contacts.data.attributes.PhoneNumber;
import contacts.input.validators.Validator;

public class Person extends ContactDetails {
    private final String firstname;
    private final String surname;
    private final Gender gender;
    private final PhoneNumber phoneNumber;

    private Person(String firstname, String surname, Gender gender, Address address, PhoneNumber phoneNumber) {
        super(address, phoneNumber);
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected String getKeyInternal() {
        return null;
    }

    @Override
    public String getKey() {
        return surname + "," + firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", phoneNumber=" + phoneNumber +
                "} " + super.toString();
    }

    public static class Builder {
        private final Validator nameValidator;

        private String firstname;
        private String surname;
        private Gender gender;
        private Address address;
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

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder from(Person other) {
            this.firstname = other.firstname;
            this.surname = other.surname;
            this.address = other.address;
            this.gender = other.gender;
            this.phoneNumber = other.phoneNumber; // we can do this, phone number is immutable
            return this;
        }

        public Person build() {
            if (nameValidator.validate(firstname) && nameValidator.validate(surname)) {
                return new Person(firstname, surname, gender, address, phoneNumber);
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
                    ", gender=" + gender +
                    ", address=" + address +
                    ", phoneNumber=" + phoneNumber +
                    '}';
        }
    }
}

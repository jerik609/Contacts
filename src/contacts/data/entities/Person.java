package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.Gender;
import contacts.data.attributes.PhoneNumber;
import contacts.input.validators.Validator;

public class Person extends ContactDetails {
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

    public String getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + firstname + "\n" +
                "Surname: " + firstname + "\n" +
                "Birth date: " + surname + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + phoneNumber + "\n" +
                super.toString();
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
}

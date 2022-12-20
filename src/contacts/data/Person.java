package contacts.data;

import contacts.pool.Keyed;
import contacts.validators.Validator;

public class Person extends ContactDetails implements Keyed {
    private final String firstname;
    private final String surname;
    private final PhoneNumber phoneNumber;

    private Person(String firstname, String surname, PhoneNumber phoneNumber) {
        this.firstname = firstname;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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
        return firstname + " "
                + surname + ", "
                + (phoneNumber == null ? "[no number]" : phoneNumber.getPhoneNumber());
    }

    public static class PersonBuilder {
        private final Validator nameValidator;

        private String firstname;
        private String surname;
        private PhoneNumber phoneNumber;

        public PersonBuilder(Validator nameValidator) {
            this.nameValidator = nameValidator;
        }

        public PersonBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public PersonBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder phoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonBuilder fromOther(Person other) {
            this.firstname = other.firstname;
            this.surname = other.surname;
            this.phoneNumber = other.phoneNumber; // we can do this, phone number is immutable
            return this;
        }

        public Person build() {
            if (nameValidator.validate(firstname) && nameValidator.validate(surname)) {
                return new Person(firstname, surname, phoneNumber);
            } else {
                System.out.println("Invalid person: " + this);
                return null;
            }
        }

        @Override
        public String toString() {
            return surname + ", " + firstname + ", " + phoneNumber;
        }
    }
}

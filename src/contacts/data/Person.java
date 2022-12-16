package contacts.data;

import contacts.pool.Poolable;
import contacts.validators.PersonValidator;

public class Person implements Poolable {

    private final String firstname;
    private final String surname;
    private final String phoneNumber;

    private Person(String firstname, String surname, String phoneNumber) {
        this.firstname = firstname;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getKey() {
        return surname + "," + firstname;
    }

    @Override
    public String toString() {
        return surname + "," + firstname + "," + phoneNumber;
    }

    public static class PersonBuilder {

        private String firstname;
        private String surname;
        private String phoneNumber;

        public PersonBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Person build() {
            if (PersonValidator.isValidName(firstname) &&
                    PersonValidator.isValidName(surname) &&
                    PersonValidator.isValidPhoneNumber(phoneNumber)) {
                return new Person(firstname, surname, phoneNumber);
            } else {
                System.out.println("Invalid person: " + this);
                return null;
            }
        }

        @Override
        public String toString() {
            return surname + "," + firstname + "," + phoneNumber;
        }
    }
}

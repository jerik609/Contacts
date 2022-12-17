package contacts.data;

import contacts.validators.PhoneNumberValidator;
import contacts.validators.Validator;

public class PhoneNumber {
    private final static Validator phoneNumberValidator = new PhoneNumberValidator();

    private final String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber buildPhoneNumber(String phoneNumber) {
        if (phoneNumberValidator.validate(phoneNumber)) {
            return new PhoneNumber(phoneNumber);
        } else {
            return null;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

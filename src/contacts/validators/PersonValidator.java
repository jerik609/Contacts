package contacts.validators;

import contacts.data.PhoneNumber;

public class PersonValidator {

    private static final String NAME_REGEX = "\\w+";

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber != null;
    }

}

package contacts.validators;

public class PersonValidator {

    private static final String NAME_REGEX = "[a-zA-Z]+";
    private static final String PHONE_NUMBER_REGEX = "[0-9]-[0-9]{3}-[0-9]{3}-[0-9]{3}";

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

}

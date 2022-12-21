package contacts.input.validators;

public class NameValidator implements Validator {
    private static final String NAME_REGEX = "\\w+";

    @Override
    public boolean validate(String input) {
        return input.matches(NAME_REGEX);
    }
}

package contacts.validators;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator {
    private static final String REGEX_SPLIT = "[ -]";
    private static final String REGEX_PARENTHESES_SEARCH = "\\([\\w\\s]+\\)";
    private static final String REGEX_FIRST_GROUP = "\\+?\\(?[a-zA-Z0-9]+\\)?";
    private static final String REGEX_DEFAULT_GROUP = "\\(*\\w{2,}\\)*";

    private static final Pattern parenthesesPattern = Pattern.compile(REGEX_PARENTHESES_SEARCH);
    private static final Pattern firstGroupPattern = Pattern.compile(REGEX_FIRST_GROUP);
    private static final Pattern defaultGroupPattern = Pattern.compile(REGEX_DEFAULT_GROUP);

    @Override
    public boolean validate(String input) {
        try {
            // split and check length
            var split = input.split(REGEX_SPLIT);
            if (split.length == 0) {
                throw new InvalidParameterException("Empty phone number");
            }

            // validate first group
            if (!firstGroupPattern.matcher(split[0]).matches()) {
                throw new InvalidParameterException("Invalid first group: " + split[0]);
            }

            // if more than one group
            if (split.length > 1) {
                // validate parentheses
                var parenthesesCount = parenthesesPattern.matcher(input).results().count();
                if (parenthesesCount > 1) { // more than one set of parentheses
                    throw new InvalidParameterException("Invalid number of parentheses: " + input);
                } else if (parenthesesCount == 1) { // parentheses not in first two groups
                    if (!parenthesesPattern.matcher(split[0]).matches() && !parenthesesPattern.matcher(split[1]).matches()) {
                        throw new InvalidParameterException("Parentheses not in first two groups: " + input);
                    }
                }

                // validate remaining groups
                for (int i = 1; i < split.length; i++) {
                    if (!defaultGroupPattern.matcher(split[i]).matches()) {
                        throw new InvalidParameterException("Invalid group: " + split[i]);
                    }
                }
            }
        } catch (InvalidParameterException e) {
            //System.out.println(e.getMessage());
            System.out.println("Wrong number format!");
            return false;
        }
        return true;
    }
}

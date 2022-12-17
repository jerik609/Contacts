package contacts.data;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class PhoneNumber {

    private static final String REGEX_SPLIT = "[ -]";
    private static final String REGEX_PARENTHESES_SEARCH = "\\([\\w\\s]+\\)";
    private static final String REGEX_FIRST_GROUP = "\\+?\\(?[a-zA-Z0-9]+\\)?";
    private static final String REGEX_DEFAULT_GROUP = "\\(*\\w{2,}\\)*";

    private static final Pattern parenthesesPattern = Pattern.compile(REGEX_PARENTHESES_SEARCH);
    private static final Pattern firstGroupPattern = Pattern.compile(REGEX_FIRST_GROUP);
    private static final Pattern defaultGroupPattern = Pattern.compile(REGEX_DEFAULT_GROUP);

    private final String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        try {
            // split and check length
            var split = phoneNumber.split(REGEX_SPLIT);
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
                var parenthesesCount = parenthesesPattern.matcher(phoneNumber).results().count();
                if (parenthesesCount > 1) { // more than one set of parentheses
                    throw new InvalidParameterException("Invalid number of parentheses: " + phoneNumber);
                } else if (parenthesesCount == 1) { // parentheses not in first two groups
                    if (!parenthesesPattern.matcher(split[0]).matches() && !parenthesesPattern.matcher(split[1]).matches()) {
                        throw new InvalidParameterException("Parentheses not in first two groups: " + phoneNumber);
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

    public static PhoneNumber buildPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
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

//    public static void main(String[] args) {
//        var phoneNumberStr = "+(with space)"; //"+(123) 234 345-456";
//        var phoneNumber = PhoneNumber.buildPhoneNumber(phoneNumberStr);
//        if (phoneNumber != null) {
//            System.out.println(phoneNumber);
//            System.out.println(phoneNumber.getPhoneNumber());
//        } else {
//            System.out.println("Invalid phone number");
//        }
//    }
}

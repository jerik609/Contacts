package contacts.data.attributes;

import contacts.controller.selector.enums.EntityAction;

public enum Gender {
    FEMALE, MALE, NON_BINARY, ;

    public static Gender from(String value) {
        if (value.length() != 1) {
            return Gender.NON_BINARY;
        }
        return switch(value.toCharArray()[0]) {
            case 'M' -> Gender.MALE;
            case 'F' -> Gender.FEMALE;
            default -> Gender.NON_BINARY;
        };
    }
}

package contacts.data.entities;

public enum Gender {
    FEMALE, MALE, NON_BINARY, ;

    public final char initValue = this.name().charAt(0);

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

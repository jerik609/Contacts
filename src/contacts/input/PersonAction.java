package contacts.input;

public enum PersonAction {
    NAME, SURNAME, BIRTH, GENDER, NUMBER, UNKNOWN ;

    public static PersonAction translateToMenuAction(String input) {
        try {
            return PersonAction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return PersonAction.UNKNOWN;
        }
    }
}

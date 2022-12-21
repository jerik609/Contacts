package contacts.input;

public enum OrganizationAction {
    NAME, ADDRESS, NUMBER, UNKNOWN ;

    public static OrganizationAction translateToMenuAction(String input) {
        try {
            return OrganizationAction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return OrganizationAction.UNKNOWN;
        }
    }
}

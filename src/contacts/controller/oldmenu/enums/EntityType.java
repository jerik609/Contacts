package contacts.controller.oldmenu.enums;

public enum EntityType {
    PERSON, ORGANIZATION, UNKNOWN;

    private String originalValue;

    public String getOriginalValue() {
        return originalValue;
    }

    public static EntityType translateFrom(String input) {
        EntityType action;
        try {
            action = EntityType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            action = EntityType.UNKNOWN;
        }
        action.originalValue = input;
        return action;
    }
}

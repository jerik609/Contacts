package contacts.controller.menu.enums;

public enum EntityAction {
    ADD, REMOVE, EDIT, COUNT, INFO, EXIT, UNKNOWN;

    private String originalValue;

    public String getOriginalValue() {
        return originalValue;
    }

    public static EntityAction translateFrom(String input) {
        EntityAction action;
        try {
            action = EntityAction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            action = EntityAction.UNKNOWN;
        }
        action.originalValue = input;
        return action;
    }
}

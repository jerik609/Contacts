package contacts.menu.selector;

public enum Action {
    ADD, REMOVE, EDIT, COUNT, INFO, EXIT, UNKNOWN ;

    public static Action translateToMenuAction(String input) {
        try {
            return Action.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Action.UNKNOWN;
        }
    }
}

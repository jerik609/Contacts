package contacts.input;

public enum MenuAction {
    ADD, REMOVE, EDIT, COUNT, LIST, EXIT, UNKNOWN ;

    public static MenuAction translateToMenuAction(String input) {
        try {
            return MenuAction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return MenuAction.UNKNOWN;
        }
    }

}

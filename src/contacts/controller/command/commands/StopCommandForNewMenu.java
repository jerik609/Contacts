package contacts.controller.command.commands;

import contacts.controller.command.Command;
import contacts.controller.newmenu.Menu;

public class StopCommandForNewMenu implements Command {
    private final Menu menu;

    public StopCommandForNewMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stop();
    }
}

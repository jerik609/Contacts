package contacts.controller.command.commands;

import contacts.controller.command.Command;
import contacts.controller.menu.Menu;

public class StopCommand implements Command {
    private final Menu menu;

    public StopCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stop();
    }
}

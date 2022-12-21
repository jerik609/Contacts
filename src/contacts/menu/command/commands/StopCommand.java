package contacts.menu.command.commands;

import contacts.menu.command.Command;
import contacts.menu.Controller;

public class StopCommand implements Command {
    private final Controller menu;

    public StopCommand(Controller menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.stop();
    }
}

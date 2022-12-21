package contacts.controller.command.commands;

import contacts.controller.command.Command;
import contacts.controller.Controller;

public class StopCommand implements Command {
    private final Controller menu;

    public StopCommand(Controller menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        //System.out.println("Stopping...");
        menu.stop();
    }
}

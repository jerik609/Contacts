package contacts.controller.command.commands;

import contacts.controller.command.Command;

public class NoopCommand implements Command {

    private final String name;

    public NoopCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("NoopCommand: " + name);
    }
}

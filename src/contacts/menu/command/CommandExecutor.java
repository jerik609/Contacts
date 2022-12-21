package contacts.menu.command;

import java.util.LinkedList;
import java.util.Queue;

public class CommandExecutor {
    private final Queue<Command> commandQueue = new LinkedList<>();

    public void acceptCommand(Command command) {
        commandQueue.add(command);
    }

    public void executeCommands() {
        while (!commandQueue.isEmpty()) {
            commandQueue.remove().execute();
        }
    }

}

package by.epam.pizzaria.bean;

import by.epam.pizzaria.controller.command.CommandName;

/**
 * Base Request class used for transporting request between layers. <br>
 *
 * @author Polina Krukovich
 */

public class Request {

    private CommandName commandName;

    public CommandName getCommandName() {
        return commandName;
    }

    public void setCommandName(CommandName commandName) {
        this.commandName = commandName;
    }
}

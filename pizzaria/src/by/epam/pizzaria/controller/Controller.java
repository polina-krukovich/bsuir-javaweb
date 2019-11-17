package by.epam.pizzaria.controller;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.controller.command.CommandName;
import by.epam.pizzaria.controller.command.CommandStatus;

/**
 * This class represents Controller Layer which implements connection between View and Service
 *
 * @author Polina Krukovich
 */

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    /**
     * Execute requested command and return response
     * @param request {@link Request} object with filled in {@link CommandName}
     * @return Returns {@link Response} object which contains {@link CommandStatus} and additional data
     * @see Request
     * @see Response
     * @see CommandStatus
     * @see CommandName
     */
    public Response executeTask(Request request) {
        return provider.getCommand(request.getCommandName()).execute(request);
    }
}

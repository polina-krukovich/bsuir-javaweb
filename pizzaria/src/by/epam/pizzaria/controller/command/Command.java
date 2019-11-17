package by.epam.pizzaria.controller.command;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.bean.Response;

public interface Command {
    /**
     * Execute requested command and return response
     * @param request {@link Request} object with filled in {@link CommandName}
     * @return Returns {@link Response} object which contains {@link CommandStatus} and additional data
     * @see Request
     * @see Response
     * @see CommandStatus
     * @see CommandName
     */
    Response execute(Request request);
}

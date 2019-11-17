package by.epam.pizzaria.controller.command.implementation.user;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.bean.user.UserRequest;
import by.epam.pizzaria.bean.user.UserResponse;
import by.epam.pizzaria.controller.command.Command;
import by.epam.pizzaria.controller.command.CommandStatus;
import by.epam.pizzaria.service.ClientService;
import by.epam.pizzaria.service.exception.ServiceException;
import by.epam.pizzaria.service.factory.ServiceFactory;

public class SignOut implements Command {
    /**
     * Execute Sign Out command
     * @param request {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Response execute(Request request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getUserService();

        UserResponse userResponse = new UserResponse();

        try {
            clientService.signOut();
        } catch (ServiceException e) {
            userResponse.setErrorMessage(e.getMessage());
            userResponse.setStatus(CommandStatus.ERROR);
            return userResponse;
        }
        userResponse.setSuccessMessage("Successfully signed out");
        userResponse.setStatus(CommandStatus.SUCCESS);
        return userResponse;
    }
}

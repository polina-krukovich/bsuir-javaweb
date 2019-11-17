package by.epam.pizzaria.controller.command.implementation.pizza;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.bean.pizza.PizzaRequest;
import by.epam.pizzaria.bean.pizza.PizzaResponse;
import by.epam.pizzaria.controller.command.Command;
import by.epam.pizzaria.controller.command.CommandStatus;
import by.epam.pizzaria.service.PizzariaService;
import by.epam.pizzaria.service.exception.ServiceException;
import by.epam.pizzaria.service.factory.ServiceFactory;

public class ReadPizzaAll implements Command {
    /**
     * Execute Read All Pizzas command
     * @param request {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Response execute(Request request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PizzariaService pizzaService = serviceFactory.getPizzaService();

        PizzaResponse pizzaResponse = new PizzaResponse();

        try {
            pizzaResponse.setPizzas(pizzaService.readAllPizzas());
        } catch (ServiceException e) {
            pizzaResponse.setErrorMessage(e.getMessage());
            pizzaResponse.setStatus(CommandStatus.ERROR);
            return pizzaResponse;
        }
        pizzaResponse.setSuccessMessage("Successfully read pizzas");
        pizzaResponse.setStatus(CommandStatus.SUCCESS);
        return pizzaResponse;
    }
}

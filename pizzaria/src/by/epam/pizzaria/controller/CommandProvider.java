package by.epam.pizzaria.controller;

import by.epam.pizzaria.controller.command.Command;
import by.epam.pizzaria.controller.command.CommandName;
import by.epam.pizzaria.controller.command.implementation.pizza.*;
import by.epam.pizzaria.controller.command.implementation.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides simple mechanism for accessing command instances.
 *
 * @author Polina Krukovich
 */

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.CREATE_PIZZA, new CreatePizza());
        repository.put(CommandName.READ_PIZZA_BY_NAME, new ReadPizzaByName());
        repository.put(CommandName.READ_PIZZA_BY_ID, new ReadPizzaById());
        repository.put(CommandName.READ_PIZZA_ALL, new ReadPizzaAll());
        repository.put(CommandName.UPDATE_PIZZA_NAME, new UpdatePizzaName());
        repository.put(CommandName.UPDATE_PIZZA_DESCRIPTION, new UpdatePizzaDescription());
        repository.put(CommandName.UPDATE_PIZZA_PRICE, new UpdatePizzaPrice());
        repository.put(CommandName.DELETE_PIZZA, new DeletePizza());
    }

    public Command getCommand(CommandName commandName) {
        return repository.get(commandName);
    }
}

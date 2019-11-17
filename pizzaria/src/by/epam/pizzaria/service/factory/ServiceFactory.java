package by.epam.pizzaria.service.factory;

import by.epam.pizzaria.service.ClientService;
import by.epam.pizzaria.service.PizzariaService;
import by.epam.pizzaria.service.implementation.PizzaService;
import by.epam.pizzaria.service.implementation.UserService;
/**
 * Singleton class for creating instances for Service Layer
 *
 * @author Polina Krukovich
 */
public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final ClientService userService = new UserService();
    private final PizzariaService pizzaService = new PizzaService();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientService getUserService() {
        return userService;
    }

    public PizzariaService getPizzaService() {
        return pizzaService;
    }
}

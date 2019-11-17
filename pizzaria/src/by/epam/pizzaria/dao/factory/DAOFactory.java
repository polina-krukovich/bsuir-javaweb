package by.epam.pizzaria.dao.factory;

import by.epam.pizzaria.dao.PizzaDAO;
import by.epam.pizzaria.dao.UserDAO;
import by.epam.pizzaria.dao.implementation.XMLPizzaDAO;
import by.epam.pizzaria.dao.implementation.XMLUserDAO;


/**
 * Singleton class for creating instances for Data Access Layer
 *
 * @author Polina Krukovich
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final PizzaDAO xmlPizzaDAO = new XMLPizzaDAO();
    private final UserDAO xmlUserDAO = new XMLUserDAO();

    private DAOFactory() { }

    public static DAOFactory getInstance() {
        return instance;
    }

    public PizzaDAO getPizzaDAO() {
        return xmlPizzaDAO;
    }

    public UserDAO getUserDAO() {
        return xmlUserDAO;
    }
}

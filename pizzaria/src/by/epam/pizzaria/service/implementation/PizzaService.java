package by.epam.pizzaria.service.implementation;

import by.epam.pizzaria.dao.PizzaDAO;
import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.dao.factory.DAOFactory;
import by.epam.pizzaria.entity.Pizza;
import by.epam.pizzaria.service.PizzariaService;
import by.epam.pizzaria.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents services connected with pizza for Service Layer
 */
public class PizzaService implements PizzariaService {
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPizza(Pizza pizza) throws ServiceException{
        if (pizza == null)
            throw new ServiceException("Incorrect data");
        if (pizza.getName() == null || pizza.getName().isEmpty())
            throw new ServiceException("Incorrect pizza name");
        if (pizza.getDescription() == null || pizza.getDescription().isEmpty())
            throw new ServiceException("Incorrect pizza description");
        if (pizza.getPrice() <= 0) {
            throw new ServiceException("Incorrect pizza price");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzaDAO.createPizza(pizza);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pizza readPizza(String name) throws ServiceException {
        if (name == null || name.isEmpty())
            throw new ServiceException("Incorrect pizza name");

        Pizza pizza;
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizza = pizzaDAO.readPizza(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return pizza;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pizza readPizza(int id) throws ServiceException {
        Pizza pizza;
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizza = pizzaDAO.readPizza(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return pizza;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pizza> readAllPizzas() throws ServiceException {
        List<Pizza> pizzas = new ArrayList<>();
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzas = pizzaDAO.readAllPizzas();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return pizzas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaName(int id, String newName) throws ServiceException {
        if (newName == null || newName.isEmpty())
            throw new ServiceException("Incorrect pizza name");

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzaDAO.updatePizzaName(id, newName);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaDescription(int id, String newDescription) throws ServiceException {
        if (newDescription == null || newDescription.isEmpty())
            throw new ServiceException("Incorrect pizza description");

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzaDAO.updatePizzaDescription(id, newDescription);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaPrice(int id, double newPrice) throws ServiceException {
        if (newPrice <= 0)
            throw new ServiceException("Incorrect pizza price");

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzaDAO.updatePizzaPrice(id, newPrice);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePizza(int id) throws ServiceException {
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            PizzaDAO pizzaDAO = daoObjectFactory.getPizzaDAO();
            pizzaDAO.deletePizza(id);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

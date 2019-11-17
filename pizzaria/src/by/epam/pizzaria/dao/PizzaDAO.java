package by.epam.pizzaria.dao;

import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.entity.Pizza;

import java.util.List;

public interface PizzaDAO {
    /**
     * Add new pizza
     * @param pizza {@link Pizza} object
     * @throws DAOException Data Access Layer exception
     */
    void createPizza(Pizza pizza) throws DAOException;

    /**
     * Find pizza by name
     * @param name requested pizza name
     * @return Returns requested {@link Pizza} object if found
     * @throws DAOException Data Access Layer exception
     */
    Pizza readPizza(String name) throws DAOException;

    /**
     * Find pizza by id
     * @param id requested pizza id
     * @return Returns requested {@link Pizza} object if found
     * @throws DAOException DAOException Data Access Layer exception
     */
    Pizza readPizza(int id) throws DAOException;

    /**
     * Find all pizzas
     * @return Returns {@link List} of requested {@link Pizza} objects if found
     * @throws DAOException DAOException Data Access Layer exception
     */
    List<Pizza> readAllPizzas() throws DAOException;

    /**
     * Find pizza by id and update name
     * @param id requested pizza id
     * @param newName new pizza name
     * @throws DAOException DAOException Data Access Layer exception
     */
    void updatePizzaName(int id, String newName) throws DAOException;

    /**
     * Find pizza by id and update description
     * @param id requested pizza id
     * @param newDescription new pizza description
     * @throws DAOException DAOException Data Access Layer exception
     */
    void updatePizzaDescription(int id, String newDescription) throws DAOException;

    /**
     * Find pizza by id and update price
     * @param id requested pizza id
     * @param newPrice new pizza price
     * @throws DAOException DAOException Data Access Layer exception
     */
    void updatePizzaPrice(int id, double newPrice) throws DAOException;

    /**
     * Find pizza by id and delete it
     * @param id requested pizza id
     * @throws DAOException DAOException Data Access Layer exception
     */
    void deletePizza(int id) throws DAOException;
}

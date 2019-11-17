package by.epam.pizzaria.service;

import by.epam.pizzaria.entity.Pizza;
import by.epam.pizzaria.service.exception.ServiceException;

import java.util.List;

public interface PizzariaService {
    /**
     * Add new pizza
     * @param pizza {@link Pizza} object
     * @throws ServiceException Service Layer exception
     */
    void createPizza(Pizza pizza) throws ServiceException;

    /**
     * Find pizza by name
     * @param name requested pizza name
     * @return Returns requested {@link Pizza} object if found
     * @throws ServiceException Service Layer exception
     */
    Pizza readPizza(String name) throws ServiceException;

    /**
     * Find pizza by id
     * @param id requested pizza id
     * @return Returns requested {@link Pizza} object if found
     * @throws ServiceException Service Layer exception
     */
    Pizza readPizza(int id) throws ServiceException;

    /**
     * Find all pizzas
     * @return Returns {@link List} of requested {@link Pizza} objects if found
     * @throws ServiceException Service Layer exception
     */
    List<Pizza> readAllPizzas() throws ServiceException;

    /**
     * Update name
     * @param id requested pizza id
     * @param newName new pizza name
     * @throws ServiceException Service Layer exception
     */
    void updatePizzaName(int id, String newName) throws ServiceException;

    /**
     * Update description
     * @param id requested pizza id
     * @param newDescription new pizza description
     * @throws ServiceException Service Layer exception
     */
    void updatePizzaDescription(int id, String newDescription) throws ServiceException;

    /**
     * Update price
     * @param id requested pizza id
     * @param newPrice new pizza price
     * @throws ServiceException Service Layer exception
     */
    void updatePizzaPrice(int id, double newPrice) throws ServiceException;

    /**
     * Delete pizza
     * @param id requested pizza id
     * @throws ServiceException Service Layer exception
     */
    void deletePizza(int id) throws ServiceException;
}

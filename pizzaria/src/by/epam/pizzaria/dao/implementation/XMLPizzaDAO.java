package by.epam.pizzaria.dao.implementation;

import by.epam.pizzaria.dao.PizzaDAO;
import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.entity.Pizza;
import by.epam.pizzaria.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class XMLPizzaDAO implements PizzaDAO {

    private List<Pizza> pizzas = new ArrayList<>();
    private String pizzaPath = "D:\\study\\ВТ\\pizzaria\\src\\by\\epam\\pizzaria\\source\\pizzas.xml";
    private User authUser;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPizza(Pizza pizza) throws DAOException {
        deserializeAuth();
        if (authUser == null || !authUser.isAdmin()) {
            throw new DAOException("Only admin can perform this operation");
        }
        //deserialize();
        for (Pizza existingPizza : pizzas) {
            if (existingPizza.getName().equals(pizza.getName())) {
                throw new DAOException("Pizza with name '" + pizza.getName() + "' already exists");
            }
        }
        int maxID = 0;
        for (Pizza existingPizza : pizzas) {
            maxID = Math.max(existingPizza.getId(), maxID);
        }
        pizza.setId(maxID + 1);
        pizzas.add(pizza);
        serialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pizza readPizza(String name) throws DAOException{
        deserializeAuth();
        if (authUser == null) {
            throw new DAOException("Only authorized users can perform this operation");
        }
        deserialize();
        for (Pizza existingPizza : pizzas) {
            if (existingPizza.getName().equals(name)) {
                return existingPizza;
            }
        }
        throw new DAOException("Pizza with name '" + name + "' does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pizza readPizza(int id) throws DAOException{
        deserializeAuth();
        if (authUser == null) {
            throw new DAOException("Only authorized users can perform this operation");
        }
        deserialize();
        for (Pizza existingPizza : pizzas) {
            if (existingPizza.getId() == id) {
                return existingPizza;
            }
        }
        throw new DAOException("Pizza with id " + id + " does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pizza> readAllPizzas() throws DAOException{
        deserializeAuth();
        if (authUser == null) {
            throw new DAOException("Only authorized users can perform this operation");
        }
        deserialize();
        return pizzas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaName(int id, String newName) throws DAOException{
        deserializeAuth();
        if (authUser == null || !authUser.isAdmin()) {
            throw new DAOException("Only admin can perform this operation");
        }
        deserialize();
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                pizza.setName(newName);
                serialize();
                return;
            }
        }
        throw new DAOException("Pizza with id " + id + " does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaDescription(int id, String newDescription) throws DAOException{
        deserializeAuth();
        if (authUser == null || !authUser.isAdmin()) {
            throw new DAOException("Only admin can perform this operation");
        }
        deserialize();
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                pizza.setDescription(newDescription);
                serialize();
                return;
            }
        }
        throw new DAOException("Pizza with id " + id + " does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePizzaPrice(int id, double newPrice) throws DAOException{
        deserializeAuth();
        if (authUser == null || !authUser.isAdmin()) {
            throw new DAOException("Only admin can perform this operation");
        }
        deserialize();
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                pizza.setPrice(newPrice);
                serialize();
                return;
            }
        }
        throw new DAOException("Pizza with id " + id + " does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePizza(int id) throws DAOException{
        deserializeAuth();
        if (authUser == null || !authUser.isAdmin()) {
            throw new DAOException("Only admin can perform this operation");
        }
        deserialize();
        int index = -1;
        for (Pizza pizza : pizzas)
            if (pizza.getId() == id) {
                index = pizzas.indexOf(pizza);
                break;
            }
        if (index < 0) {
            throw new DAOException("Pizza with id " + id + " does not exist");
        } else {
            pizzas.remove(index);
        }
        serialize();
    }

    private void serialize() throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(pizzaPath)) {
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(pizzas);
            encoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }

    private void deserialize() throws DAOException {
        try (FileInputStream fis = new FileInputStream(pizzaPath)){
            XMLDecoder decoder = new XMLDecoder(fis);
            pizzas = (List<Pizza>)decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }

    private void deserializeAuth() throws DAOException {
        String authPath = "D:\\study\\ВТ\\pizzaria\\src\\by\\epam\\pizzaria\\source\\auth.xml";
        try (FileInputStream fis = new FileInputStream(authPath)){
            XMLDecoder decoder = new XMLDecoder(fis);
            authUser = (User)decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }
}

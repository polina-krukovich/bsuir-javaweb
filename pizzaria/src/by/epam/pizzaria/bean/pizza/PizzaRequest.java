package by.epam.pizzaria.bean.pizza;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.entity.Pizza;

/**
 * This class represents request for CRUD operation connected with Pizza entity
 *
 * @author Polina Krukovich
 * @see Pizza
 * @see Request
 */

public class PizzaRequest extends Request {
    private Pizza pizza = new Pizza();

    public String getName() {
        return pizza.getName();
    }

    public void setName(String name) {
        this.pizza.setName(name);
    }

    public String getDescription() {
        return pizza.getDescription();
    }

    public void setDescription(String description) {
        this.pizza.setDescription(description);
    }

    public double getPrice() {
        return pizza.getPrice();
    }

    public void setPrice(double price) {
        this.pizza.setPrice(price);
    }

    public int getId() {
        return pizza.getId();
    }

    public void setId(int id) {
        this.pizza.setId(id);
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}

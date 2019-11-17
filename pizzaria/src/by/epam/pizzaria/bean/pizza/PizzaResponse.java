package by.epam.pizzaria.bean.pizza;

import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.entity.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents response of CRUD operation connected with Pizza entity
 *
 * @author Polina Krukovich
 * @see Pizza
 * @see Response
 */

public class PizzaResponse extends Response {
    private Pizza pizza = new Pizza();
    private List<Pizza> pizzas = new ArrayList<>();

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}

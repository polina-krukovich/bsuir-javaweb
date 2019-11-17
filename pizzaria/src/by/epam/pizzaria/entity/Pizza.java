package by.epam.pizzaria.entity;

import java.io.Serializable;

/**
 * This class represents entity that contains data about pizza.
 *
 * @author Polina Krukovich
 */

public class Pizza implements Serializable {
    private String name;
    private String description;
    private double price;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

/*
Optional<Double> totalPrice = pizzas
    .stream()
    .map(Pizza::getPrice)
    .reduce(Double::sum);
return (totalPrice.isPresent()) ? totalPrice.get() : 0;
*/
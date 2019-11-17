package entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "pizzeriaData")
public class PizzeriaData {
    @JacksonXmlElementWrapper(localName = "ExtraSections")
    @JacksonXmlProperty(localName = "extraSection")
    private List<ExtraSection> extraSections;

    @JacksonXmlElementWrapper(localName = "Extras")
    @JacksonXmlProperty(localName = "extra")
    private List<Extra> extras;

    @JacksonXmlElementWrapper(localName = "PizzaSections")
    @JacksonXmlProperty(localName = "pizzaSection")
    private List<PizzaSection> pizzaSections;

    @JacksonXmlElementWrapper(localName = "Pizzas")
    @JacksonXmlProperty(localName = "pizza")
    private List<Pizza> pizzas;

    @JacksonXmlElementWrapper(localName = "Toppings")
    @JacksonXmlProperty(localName = "topping")
    private List<Topping> toppings;

    @JacksonXmlElementWrapper(localName = "PizzaToppings")
    @JacksonXmlProperty(localName = "pizzaTopping")
    private List<PizzaTopping> pizzaToppings;

    @JacksonXmlElementWrapper(localName = "Orders")
    @JacksonXmlProperty(localName = "order")
    private List<Order> orders;

    @JacksonXmlElementWrapper(localName = "OrderedPizzas")
    @JacksonXmlProperty(localName = "orderedPizza")
    private List<OrderedPizza> orderedPizzas;

    @JacksonXmlElementWrapper(localName = "OrderedExtras")
    @JacksonXmlProperty(localName = "orderedExtra")
    private List<OrderedExtra> orderedExtras;

    @JacksonXmlElementWrapper(localName = "Users")
    @JacksonXmlProperty(localName = "user")
    private List<User> users;
}

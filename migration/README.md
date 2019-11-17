# Migration :dash:
> ***Migration*** is a simple Database migration tool intended for ***Pizzeria restaurant***.

## Entities :pizza:
All the entities used in the application inherit from class <code>Entity</code>.
```
- Extra
- ExtraSection
- Pizza
- PizzaSection
- Topping
- PizzaTopping
- Order
- OrderedExtra
- OrderedPizza
- User
```
For serialization and deserialization into/from one file 
all the entities are stored in lists in the class <code>PizzeriaData</code> 

## XML validation :heavy_check_mark:
Validation of the XML file [pizzeriaData.xml](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/src/main/resources/pizzeriaData.xml)
is performed according to the XSD scheme [pizzeriaData.xsd](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/src/main/resources/pizzeriaData.xsd) 
using <code>XmlService</code> class.


## Data migration
In this application ***MySql Driver*** is used. <code>JdbcConnectionService</code>
helps to load Jdbc Driver and establish database connection. 

Abstract class <code>Migration</code> is a super class of all the other services
intended for performing migration operations. 

## Logging :pencil:
***[Log4j](https://logging.apache.org/log4j/2.x/)*** is used for logging 
to console and file [app.log](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/src/main/resources/app.log).

![screen console log](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/screenshots/ConsoleLog.png)

## Database structure 
The Pizzeria restaurant database contains information about 
*menu*, *orders* and *users*. Different types of Database relationships are used, 
such as ***One-to-many*** and ***Many-to-many***. 

![DB structure](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/screenshots/DatabaseStructure.png)

 Such database structure makes it very convenient to use. 
 
 For example, to select all pizzas from *"Favorite"* section, you need to execute 
 the following query: 
 
 ```
SELECT pizzas.id, pizzas.name 
FROM pizza_sections
INNER JOIN pizzas ON pizzas.section_id = pizza_sections.id
where pizza_sections.name = "Favorite";
``` 

![Favorite pizzas](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/screenshots/FavoritePizzas.png)

Or, to select toppings for *"Pepperoni Blues"* pizza: 

 ```
SELECT toppings.id, toppings.name 
FROM (toppings, pizzas)
INNER JOIN (pizza_toppings) 
ON (pizza_toppings.pizza_id = pizzas.id) AND (pizza_toppings.topping_id = toppings.id)
WHERE pizzas.name = "Pepperoni Blues";
```

![Pepperoni Blues toppings](https://github.com/polina-krukovich/bsuir-javaweb/blob/master/migration/screenshots/PepperoniBluesToppings.png)

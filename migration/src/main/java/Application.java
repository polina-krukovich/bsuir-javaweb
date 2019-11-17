import entity.OrderedPizza;
import entity.PizzeriaData;
import service.exception.ServiceException;
import service.jdbc.JdbcConnectionService;
import service.migration.*;
import service.validation.XmlService;


public class Application {
    private static final String XML_FILE_PATH = "src/main/resources/pizzeriaData.xml";
    private static final String XSD_FILE_PATH = "src/main/resources/pizzeriaData.xsd";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pizzeria";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "krukovicH7";

    public static void main(String[] args) {
        try {
            XmlService xmlService = XmlService.INSTANCE;
            xmlService.validate(XML_FILE_PATH, XSD_FILE_PATH);

            PizzeriaData pizzeriaData = xmlService.read(XML_FILE_PATH);

            JdbcConnectionService.INSTANCE.establishConnection(DB_URL, DB_USER, DB_PASSWORD);

            ExtraSectionMigrationService.INSTANCE.migrate(pizzeriaData.getExtraSections());
            ExtraMigrationService.INSTANCE.migrate(pizzeriaData.getExtras());
            PizzaSectionMigrationService.INSTANCE.migrate(pizzeriaData.getPizzaSections());
            PizzaMigrationService.INSTANCE.migrate(pizzeriaData.getPizzas());
            ToppingMigrationService.INSTANCE.migrate(pizzeriaData.getToppings());
            PizzaToppingMigrationService.INSTANCE.migrate(pizzeriaData.getPizzaToppings());
            UserMigrationService.INSTANCE.migrate(pizzeriaData.getUsers());
            OrderMigrationService.INSTANCE.migrate(pizzeriaData.getOrders());
            OrderedExtraMigrationService.INSTANCE.migrate(pizzeriaData.getOrderedExtras());
            OrderedPizzaMigrationService.INSTANCE.migrate(pizzeriaData.getOrderedPizzas());
        } catch (ServiceException e) {}
    }

}

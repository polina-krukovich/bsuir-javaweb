package service.migration;

import entity.PizzaTopping;
import org.apache.logging.log4j.Level;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PizzaToppingMigrationService extends Migration<PizzaTopping> {
    public static final PizzaToppingMigrationService INSTANCE = new PizzaToppingMigrationService();

    private final String TABLE_NAME = "pizza_toppings";

    private PizzaToppingMigrationService() {}

    @Override
    public int migrate(List<PizzaTopping> pizzaToppings) throws ServiceException {
        Connection connection = retrieveConnection();

        LOGGER.log(Level.INFO, String.format(START_MIGRATION_LOG_FORMAT, TABLE_NAME));

        int migratedEntitiesCount = 0;
        for (PizzaTopping pizzaTopping : pizzaToppings) {
            migratedEntitiesCount += migrate(pizzaTopping, connection) ? 1 : 0;
        }

        LOGGER.log(Level.INFO, String.format(COMPLETE_MIGRATION_LOG_FORMAT,
                TABLE_NAME, migratedEntitiesCount, pizzaToppings.size()));

        return migratedEntitiesCount;
    }

    @Override
    protected boolean migrate(PizzaTopping pizzaTopping, Connection connection) {
        final String INSERT_QUERY = "insert into " + TABLE_NAME + "(id, pizza_id, topping_id) values (?, ?, ?)";
        boolean migrated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, pizzaTopping.getId());
            preparedStatement.setInt(2, pizzaTopping.getPizzaId());
            preparedStatement.setInt(3, pizzaTopping.getToppingId());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, String.format(SUCCESSFUL_MIGRATION_LOG_FORMAT, pizzaTopping));
            migrated = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return migrated;
    }
}

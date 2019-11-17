package service.migration;

import entity.PizzaSection;
import org.apache.logging.log4j.Level;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PizzaSectionMigrationService extends Migration<PizzaSection> {
    public static final PizzaSectionMigrationService INSTANCE = new PizzaSectionMigrationService();

    private final String TABLE_NAME = "pizza_sections";

    private PizzaSectionMigrationService() {}

    @Override
    public int migrate(List<PizzaSection> pizzaSections) throws ServiceException {
        Connection connection = retrieveConnection();

        LOGGER.log(Level.INFO, String.format(START_MIGRATION_LOG_FORMAT, TABLE_NAME));

        int migratedEntitiesCount = 0;
        for (PizzaSection pizzaSection : pizzaSections) {
            migratedEntitiesCount += migrate(pizzaSection, connection) ? 1 : 0;
        }

        LOGGER.log(Level.INFO, String.format(COMPLETE_MIGRATION_LOG_FORMAT,
                TABLE_NAME, migratedEntitiesCount, pizzaSections.size()));

        return migratedEntitiesCount;
    }

    @Override
    protected boolean migrate(PizzaSection pizzaSection, Connection connection) {
        final String INSERT_QUERY
                = "insert into " + TABLE_NAME + "(id, name, price_s, price_m, price_l) values (?, ?, ?, ?, ?)";
        boolean migrated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, pizzaSection.getId());
            preparedStatement.setString(2, pizzaSection.getName());
            preparedStatement.setDouble(3, pizzaSection.getPriceS());
            preparedStatement.setDouble(4, pizzaSection.getPriceM());
            preparedStatement.setDouble(5, pizzaSection.getPriceL());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, String.format(SUCCESSFUL_MIGRATION_LOG_FORMAT, pizzaSection));
            migrated = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return migrated;
    }
}

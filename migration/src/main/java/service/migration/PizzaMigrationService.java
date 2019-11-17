package service.migration;

import entity.Pizza;
import org.apache.logging.log4j.Level;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PizzaMigrationService extends Migration<Pizza> {
    public static final PizzaMigrationService INSTANCE = new PizzaMigrationService();

    private final String TABLE_NAME = "pizzas";

    private PizzaMigrationService() {}

    @Override
    public int migrate(List<Pizza> pizzas) throws ServiceException {
        Connection connection = retrieveConnection();

        LOGGER.log(Level.INFO, String.format(START_MIGRATION_LOG_FORMAT, TABLE_NAME));

        int migratedEntitiesCount = 0;
        for (Pizza pizza : pizzas) {
            migratedEntitiesCount += migrate(pizza, connection) ? 1 : 0;
        }

        LOGGER.log(Level.INFO, String.format(COMPLETE_MIGRATION_LOG_FORMAT,
                TABLE_NAME, migratedEntitiesCount, pizzas.size()));

        return migratedEntitiesCount;
    }

    @Override
    protected boolean migrate(Pizza pizza, Connection connection) {
        final String INSERT_QUERY = "insert into " + TABLE_NAME
                + "(id, name, weight_s, weight_m, weight_l, section_id) values (?, ?, ?, ?, ?, ?)";
        boolean migrated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, pizza.getId());
            preparedStatement.setString(2, pizza.getName());
            preparedStatement.setInt(3, pizza.getWeightS());
            preparedStatement.setInt(4, pizza.getWeightM());
            preparedStatement.setInt(5, pizza.getWeightL());
            preparedStatement.setDouble(6, pizza.getSectionId());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, String.format(SUCCESSFUL_MIGRATION_LOG_FORMAT, pizza));
            migrated = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return migrated;
    }
}

package service.migration;

import entity.OrderedPizza;
import org.apache.logging.log4j.Level;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderedPizzaMigrationService extends Migration<OrderedPizza> {
    public static final OrderedPizzaMigrationService INSTANCE = new OrderedPizzaMigrationService();

    private final String TABLE_NAME = "ordered_pizzas";

    private OrderedPizzaMigrationService() {}

    @Override
    public int migrate(List<OrderedPizza> orderedPizzas) throws ServiceException {
        Connection connection = retrieveConnection();

        LOGGER.log(Level.INFO, String.format(START_MIGRATION_LOG_FORMAT, TABLE_NAME));

        int migratedEntitiesCount = 0;
        for (OrderedPizza orderedPizza : orderedPizzas) {
            migratedEntitiesCount += migrate(orderedPizza, connection) ? 1 : 0;
        }

        LOGGER.log(Level.INFO, String.format(COMPLETE_MIGRATION_LOG_FORMAT,
                TABLE_NAME, migratedEntitiesCount, orderedPizzas.size()));

        return migratedEntitiesCount;
    }

    @Override
    protected boolean migrate(OrderedPizza orderedPizza, Connection connection) {
        final String INSERT_QUERY = "insert into " + TABLE_NAME
                + "(id, size, count, pizza_id, order_id) values (?, ?, ?, ?, ?)";
        boolean migrated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, orderedPizza.getId());
            preparedStatement.setInt(2, orderedPizza.getSize().ordinal());
            preparedStatement.setInt(3, orderedPizza.getCount());
            preparedStatement.setInt(4, orderedPizza.getPizzaId());
            preparedStatement.setInt(5, orderedPizza.getOrderId());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, String.format(SUCCESSFUL_MIGRATION_LOG_FORMAT, orderedPizza));
            migrated = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return migrated;
    }
}

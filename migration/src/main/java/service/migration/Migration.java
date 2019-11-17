package service.migration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.ServiceException;
import service.jdbc.JdbcConnectionService;

import java.sql.Connection;
import java.util.List;

/**
 * This is an abstract class for migration of data to the database.
 *
 * @param <T> the type of Entities to migrate
 * @see JdbcConnectionService
 *
 * @author Polina Krukovich
 */
public abstract class Migration<T> {
    final JdbcConnectionService jdbcConnectionService = JdbcConnectionService.INSTANCE;
    final Logger LOGGER = LogManager.getLogger(getClass());
    final String START_MIGRATION_LOG_FORMAT = "MIGRATION STARTED: %s.";
    final String COMPLETE_MIGRATION_LOG_FORMAT = "MIGRATION COMPLETED: %s, %d/%d.";
    final String SUCCESSFUL_MIGRATION_LOG_FORMAT = "%s migrated successfully.";

    /**
     * This method performs migration of the entities list to the database table
     * @param entities the list of entities to migrate
     * @return number of migrated entities
     * @throws ServiceException if the connection was not established.
     */
    public abstract int migrate(List<T> entities) throws ServiceException;

    /**
     * This method performs migration of an entity to the database table
     * @param entity the entity to migrate
     * @param connection database <code>Connection</code> object
     * @return <code>true</code> if the entity migrated successfully,
     * <code>false</code> - if entity was not migrated
     */
    protected abstract boolean migrate(T entity, Connection connection);

    protected Connection retrieveConnection() throws ServiceException{
        Connection connection;
        try {
            connection = jdbcConnectionService.getConnection();
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw e;
        }
        return connection;
    }
}

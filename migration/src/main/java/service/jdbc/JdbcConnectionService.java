package service.jdbc;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a Singleton class that provides a basic service for managing a connection to the Database.
 *
 * @see Connection
 * @see DriverManager
 * @author Polina Krukovich
 */
public class JdbcConnectionService implements JdbcConnection {
    public static final JdbcConnectionService INSTANCE = new JdbcConnectionService();

    private Connection connection;
    private final Logger LOGGER = LogManager.getLogger(getClass());

    private JdbcConnectionService() {}

    /**
     * This method loads JDBC driver using <code>Class.forName()</code>
     * and establishes a connection to the given database URL.
     *
     * @param url a database URL
     * @param user the database user on whose behalf the connection is being made
     * @param password the user's password
     * @throws ServiceException the JDBC driver is not found, a database access error occurs
     * or timeout has been exceeded
     */
    @Override
    public void establishConnection(String url, String user, String password) throws ServiceException {
        try {
            // load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establish connection
            connection = DriverManager.getConnection(url, user, password);
            LOGGER.log(Level.INFO, "Database connection has been established successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            ServiceException serviceException = new ServiceException(e.getMessage(), e);
            LOGGER.log(Level.ERROR, serviceException.getMessage());
            throw serviceException;
        }
    }

    /**
     * This method checks if connection to the database was established
     * and returns a <code>Connection</code> object.
     *
     * @return a Connection to the database
     * @throws ServiceException if connection was not established
     */
    public Connection getConnection() throws ServiceException {
        if (connection == null) {
            ServiceException serviceException = new ServiceException("Database connection has not been established.");
            LOGGER.log(Level.ERROR, serviceException.getMessage());
            throw serviceException;
        }
        return connection;
    }
}

package by.epam.pizzaria.dao;

import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.entity.User;

public interface UserDAO {
    /**
     * Find user depend on passed login and password
     * @param login user login
     * @param password user password
     * @throws DAOException Data Access Layer exception
     */
    void signIn(String login, String password) throws DAOException;

    /**
     * Add new user
     * @param user {@link User} object
     * @throws DAOException Data Access Layer exception
     */
    void signUp(User user) throws DAOException;

    /**
     * Remove data about signed in user
     * @throws DAOException Data Access Layer exception
     */
    void signOut() throws DAOException;
}

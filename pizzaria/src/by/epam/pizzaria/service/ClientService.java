package by.epam.pizzaria.service;

import by.epam.pizzaria.entity.User;
import by.epam.pizzaria.service.exception.ServiceException;

public interface ClientService {

    /**
     * Validate passed parameters and sign in user
     * @param login user login
     * @param password user password
     * @throws ServiceException Service Layer exception
     */
    void signIn(String login, String password) throws ServiceException;

    /**
     * Validate passed parameters and sign up user
     * @param user {@link User} object
     * @throws ServiceException Service Layer exception
     */
    void signUp(User user) throws ServiceException;

    /**
     * Validate passed parameters and sign out user
     * @throws ServiceException Service Layer exception
     */
    void signOut() throws ServiceException;
}

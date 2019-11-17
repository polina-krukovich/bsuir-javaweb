package by.epam.pizzaria.service.implementation;

import by.epam.pizzaria.dao.UserDAO;
import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.dao.factory.DAOFactory;
import by.epam.pizzaria.entity.User;
import by.epam.pizzaria.service.ClientService;
import by.epam.pizzaria.service.exception.ServiceException;

/**
 * This class represents services connected with user for Service Layer
 *
 * @author Polina Krukovich
 */
public class UserService implements ClientService {
    /**
     * {@inheritDoc}
     */
    @Override
    public void signIn(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty())
            throw new ServiceException("Incorrect login");
        if (password == null || password.isEmpty())
            throw new ServiceException("Incorrect password");

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signUp(User user) throws ServiceException {
        if (user == null)
            throw new ServiceException("Incorrect data");
        if (user.getLogin() == null || user.getLogin().isEmpty())
            throw new ServiceException(("Incorrect login"));
        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new ServiceException("Incorrect password");
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signUp(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signOut() throws ServiceException {
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signOut();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

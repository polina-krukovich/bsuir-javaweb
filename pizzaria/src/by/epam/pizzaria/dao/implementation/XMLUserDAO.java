package by.epam.pizzaria.dao.implementation;

import by.epam.pizzaria.dao.UserDAO;
import by.epam.pizzaria.dao.exception.DAOException;
import by.epam.pizzaria.entity.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of Data Access Layer for User entity with XML data source.
 *
 * @author Polina Krukovich
 */

public class XMLUserDAO implements UserDAO {
    private List<User> users = new ArrayList<>();
    private User authUser;
    private String usersPath = "D:\\study\\ВТ\\pizzaria\\src\\by\\epam\\pizzaria\\source\\users.xml";
    private String authPath = "D:\\study\\ВТ\\pizzaria\\src\\by\\epam\\pizzaria\\source\\auth.xml";

    /**
     * {@inheritDoc}
     */
    @Override
    public void signIn(String login, String password) throws DAOException {
        deserialize();
        for (User signedUser : users) {
            if (signedUser.getLogin().equals(login) && signedUser.getPassword().equals(password)) {
                authUser = signedUser;
                serializeAuth();
                return;
            }
        }
        throw new DAOException("Incorrect login or password");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signUp(User user) throws DAOException{
        deserialize();
        for (User signedUser : users) {
            if (signedUser.getLogin().equals(user.getLogin())) {
                throw new DAOException("Login '" + user.getLogin() + "' is not available");
            }
        }
        users.add(user);
        serialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signOut() throws DAOException{
        if (authUser == null) {
            throw new DAOException("Nobody is signed in");
        }
        authUser = null;
        serializeAuth();
    }

    private void serialize() throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(usersPath)) {
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(users);
            encoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }

    private void serializeAuth() throws DAOException{
        try (FileOutputStream fos = new FileOutputStream(authPath)) {
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(authUser);
            encoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }

    private void deserialize() throws DAOException {
        try (FileInputStream fis = new FileInputStream(usersPath)){
            XMLDecoder decoder = new XMLDecoder(fis);
            users = (List<User>)decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            throw new DAOException("Data file not found");
        } catch (IOException e) {
            throw new DAOException("Serialization error");
        }
    }

}

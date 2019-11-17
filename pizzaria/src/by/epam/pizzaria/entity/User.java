package by.epam.pizzaria.entity;

import java.io.Serializable;

/**
 * This class represents entity that contains data about user.
 *
 * @author Polina Krukovich
 */

public class User implements Serializable {
    private String login;
    private String password;
    private boolean admin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}

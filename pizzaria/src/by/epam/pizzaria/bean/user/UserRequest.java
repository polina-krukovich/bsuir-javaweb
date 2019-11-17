package by.epam.pizzaria.bean.user;

import by.epam.pizzaria.bean.Request;
import by.epam.pizzaria.entity.User;

/**
 * This class represents request for CRUD operation connected with User entity
 *
 * @author Polina Krukovich
 * @see User
 * @see Request
 */

public class UserRequest extends Request {
    private User user = new User();

    public String getLogin() {
        return user.getLogin();
    }

    public void setLogin(String login) {
        this.user.setLogin(login);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public boolean isAdmin() {
        return user.isAdmin();
    }

    public void setAdmin(boolean admin) {
        this.user.setAdmin(admin);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

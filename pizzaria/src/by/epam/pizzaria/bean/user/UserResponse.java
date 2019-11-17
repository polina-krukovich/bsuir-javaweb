package by.epam.pizzaria.bean.user;

import by.epam.pizzaria.bean.Response;
import by.epam.pizzaria.entity.User;

/**
 * This class represents response of CRUD operation connected with User entity
 *
 * @author Polina Krukovich
 * @see User
 * @see Response
 */

public class UserResponse extends Response {
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.service;

import com.dao.UserDAO;
import com.model.User;
import lombok.Data;

import javax.sql.DataSource;
import java.util.Optional;

@Data
public class UserService {

    private DataSource dataSource;
    private UserDAO userDAO = UserDAO.getInstance();

    {
        userDAO.setDataSource(dataSource);
    }

    private User user;
    private UserService instance;

    private UserService() {

    }

    public UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    public boolean register(String userName, String password, boolean isAdmin) {
        return userDAO.add(userName, password, isAdmin);
    }

    public boolean remove(String username) {
        return userDAO.remove(username);
    }

    public boolean changePassword(String userName, String password) {

        Optional<User> optional = userDAO.getByName(userName);

        if (optional.isPresent()) {
            boolean isAdmin = userDAO.getByName(userName).get().isAdmin();
            userDAO.update(userName, password, isAdmin);

            return true;
        }

        return false;
    }

    public boolean changeAdminStatus(String userName, boolean isAdmin) {

        Optional<User> optional = userDAO.getByName(userName);

        if (optional.isPresent()) {
            userDAO.update(userName, null, isAdmin);

            return true;
        }

        return false;
    }
}

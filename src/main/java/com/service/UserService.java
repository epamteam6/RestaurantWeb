package com.service;

import com.dao.UserDAO;
import com.model.User;
import lombok.Data;

import javax.sql.DataSource;
import java.util.Optional;

@Data
public class UserService {

    private DataSource dataSource;
    private UserDAO userDAO;
    private static UserService instance;

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    public boolean register(String userName, String password) {
        if (userDAO.getByName(userName).isPresent())
            return false;

        return userDAO.add(userName, password, false);
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

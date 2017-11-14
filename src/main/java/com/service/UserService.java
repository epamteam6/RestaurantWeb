package com.service;

import com.dao.UserDAO;
import com.model.User;
import lombok.Data;
import org.apache.log4j.Logger;

import java.util.Optional;

@Data
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    private UserDAO userDAO = UserDAO.getInstance();

    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    public boolean register(String username, String password) {

        log.info("register() " + username);

        if (password == null || username == null
                || password.length() < 1 || username.length() < 1
                || userDAO.getByName(username).isPresent())
            return false;

        return userDAO.add(username, password, false);
    }

    public boolean remove(String username) {

        log.info("remove() " + username);

        if (!userDAO.getByName(username).isPresent())
            return false;

        return userDAO.remove(username);
    }

    public boolean changePassword(String userName, String password) {

        log.info("changePassword() " + userName);

        Optional<User> optional = userDAO.getByName(userName);

        if (optional.isPresent() && password != null && password.length() > 0) {
            boolean isAdmin = optional.get().isAdmin();
            userDAO.update(userName, password, isAdmin);

            return true;
        }

        return false;
    }

    public boolean changeAdminStatus(String userName, boolean isAdmin) {

        log.info("changeAdminStatus(username, isAdmin) " + userName + ", " + isAdmin);

        Optional<User> optional = userDAO.getByName(userName);

        if (optional.isPresent()) {
            userDAO.update(userName, null, isAdmin);

            return true;
        }

        return false;
    }

    public Optional<User> getUserByName(String username) {

        log.info("getUserByName() " + username);

        return userDAO.getByName(username);
    }
}

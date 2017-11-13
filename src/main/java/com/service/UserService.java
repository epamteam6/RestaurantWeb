package com.service;

import com.dao.UserDAO;
import com.model.User;
import com.mysql.jdbc.Driver;
import lombok.Data;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;
import java.util.Optional;

@Data
public class UserService {

    private UserDAO userDAO = UserDAO.getInstance();
    private static UserService instance;

    private UserService() {

    }

    {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");


            userDAO.setDataSource(dataSource);
            setUserDAO(userDAO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();

        return instance;
    }

    public boolean register(String username, String password) {
        if (password == null || username == null
                || password.length() < 1 || username.length() < 1
                || userDAO.getByName(username).isPresent())
            return false;

        return userDAO.add(username, password, false);
    }

    public boolean remove(String username) {
        if (!userDAO.getByName(username).isPresent())
            return false;

        return userDAO.remove(username);
    }

    public boolean changePassword(String userName, String password) {

        Optional<User> optional = userDAO.getByName(userName);

        if (optional.isPresent() && password != null && password.length() > 0) {
            boolean isAdmin = optional.get().isAdmin();
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

    public Optional<User> getUserByName(String username) {
        return userDAO.getByName(username);
    }
}

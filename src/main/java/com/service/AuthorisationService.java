package com.service;

import com.dao.UserDAO;
import com.model.User;
import lombok.Data;

import javax.sql.DataSource;

@Data
public class AuthorisationService {

    private DataSource dataSource;
    private UserDAO userDAO = UserDAO.getInstance();

    {
        userDAO.setDataSource(dataSource);
    }

    private User user;
    private AuthorisationService instance;

    private AuthorisationService() {

    }

    public AuthorisationService getInstance() {
        if (instance == null)
            instance = new AuthorisationService();

        return instance;
    }

    public boolean singIn(String userName, String password) {
        if (userDAO.validate(userName, password)) {
            user = userDAO.getByName(userName).get();

            return true;
        }

        return false;
    }

    public void singOut() {
        // TODO: 02.11.2017
        user = null;
    }
}

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
        return instance;
    }

    public void singIn(String userName, String password) {
        if (userDAO.validate(userName, password)) {
            user = userDAO.getByName(userName).get();
        }
    }


}

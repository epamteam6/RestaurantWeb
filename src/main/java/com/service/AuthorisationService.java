package com.service;

import com.dao.UserDAO;
import lombok.Data;
import org.apache.log4j.Logger;


@Data
public class AuthorisationService {

    private static final Logger log = Logger.getLogger(AuthorisationService.class);

    private UserDAO userDAO = UserDAO.getInstance();

    private static AuthorisationService instance;

    private AuthorisationService() {
    }

    public static AuthorisationService getInstance() {

        if (instance == null)
            instance = new AuthorisationService();

        return instance;
    }

    public boolean singIn(String userName, String password) {

        log.info("singIn() " + userName);

        if (userDAO.validate(userName, password))
            return true;

        return false;
    }
}

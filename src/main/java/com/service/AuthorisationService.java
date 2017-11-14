package com.service;

import com.dao.UserDAO;
import com.mysql.jdbc.Driver;
import lombok.Data;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;


@Data
public class AuthorisationService {


    private UserDAO userDAO;
    private static AuthorisationService instance;

    private AuthorisationService() {
        userDAO = UserDAO.getInstance();
    }

//    {
//        try {
//            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
//                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");
//
//            userDAO = UserDAO.getInstance();
//            userDAO.setDataSource(dataSource);
//            setUserDAO(userDAO);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static AuthorisationService getInstance() {

        if (instance == null)
            instance = new AuthorisationService();

        return instance;
    }

    public boolean singIn(String userName, String password) {

        if (userDAO.validate(userName, password))
            return true;

        return false;
    }
}

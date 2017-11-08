package com.controller;

import com.dao.UserDAO;
import com.mysql.jdbc.Driver;
import com.service.UserService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;

    {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");

            userDAO = UserDAO.getInstance();

            userDAO.setDataSource(dataSource);
            userService = UserService.getInstance();
            userService.setUserDAO(userDAO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

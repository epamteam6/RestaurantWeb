package com.controller;

import com.dao.UserDAO;
import com.mysql.jdbc.Driver;
import com.service.UserService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/registration.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").include(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = userService.register(username, password);
        if (isValid) {
            System.out.println(password);

            Cookie user = new Cookie("username", username);
            response.addCookie(user);
            response.sendRedirect("/makeOrder");
            return;
        }


//        response.sendRedirect("/success.jsp");

//        System.out.println(isValid);

    }
}

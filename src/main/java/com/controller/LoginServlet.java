package com.controller;

import com.dao.UserDAO;
import com.mysql.jdbc.Driver;
import com.service.AuthorisationService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {


    private AuthorisationService authorisationService;
    private SimpleDriverDataSource dataSource;
    private UserDAO userDAO;

    {
        try {//we're planining to make connection pool instead of this
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");

            userDAO = UserDAO.getInstance();

            userDAO.setDataSource(dataSource);
            authorisationService = AuthorisationService.getInstance();
            authorisationService.setUserDAO(userDAO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/login.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").include(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = authorisationService.singIn(username, password);
        if (isValid) {
            System.out.println(password);

            Cookie user = new Cookie("username", username);
            response.addCookie(user);
            response.sendRedirect("/makeOrder");
        }
        else response.sendRedirect("login.jsp");

        System.out.println(isValid);

    }
}
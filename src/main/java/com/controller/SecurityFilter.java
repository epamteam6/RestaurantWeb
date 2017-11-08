package com.controller;

import com.dao.UserDAO;
import com.model.User;
import com.mysql.jdbc.Driver;
import com.service.UserService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SecurityFilter implements Filter {
    private UserDAO userDAO;
    private UserService userService;
    private SimpleDriverDataSource dataSource;

    {
        try {//we're planining to make connection pool instead of this
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

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();
        List<User> allUsers = userDAO.getAll();
        boolean logged = false;
        for (User user : allUsers) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    if (user.getUserName().equals(cookie.getValue())) {
                        logged = true;
                    }
                }
            }
        }


        if (logged) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/");
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }


}

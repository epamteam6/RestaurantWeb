package com.controller.session;

import com.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("(session) Initializing...");

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("session_join.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("(session) Processing...");

        request.getRequestDispatcher("session_join.jsp").include(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = userService.register(username, password);
        if (isValid) {
            System.out.println(password);

            request.getSession().setAttribute("loggedInUser", username); //we need it for session management

            Cookie user = new Cookie("username", username);
            response.addCookie(user);
            response.sendRedirect("user_create_order");

        } else {

            request.setAttribute("message", "Error! Enter your Username and Password!");
            request.getRequestDispatcher("session_join.jsp").forward(request, response);
            //response.sendRedirect("session_login_error");
        }
    }
}

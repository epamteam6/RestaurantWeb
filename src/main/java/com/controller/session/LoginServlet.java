package com.controller.session;

import com.service.AuthorisationService;
import com.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {


    private AuthorisationService authorisationService = AuthorisationService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("session_login.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("session_login.jsp").include(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = authorisationService.singIn(username, password);
        if (isValid) {
            System.out.println(password);

            boolean isAdmin = userService.getUserByName(username).get().isAdmin();

            request.getSession().setAttribute("loggedInUser", username); //we need it for session management

            Cookie user = new Cookie("username", username);
            response.addCookie(user);
            if (isAdmin) {
                user.setComment("ADMIN");
                response.sendRedirect("admin_confirmation");
            }
            else {
                user.setComment("USER");
                response.sendRedirect("user_create_order");
            }


        } else {
            request.setAttribute("message", "Error! Enter correct Username and Password!");
            request.getRequestDispatcher("session_login.jsp").forward(request, response);
        }

       // System.out.println(isValid);

    }
}
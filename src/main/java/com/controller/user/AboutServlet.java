package com.controller.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("about.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));
        request.getRequestDispatcher("about.jsp").include(request, response);
    }

}
package com.controller.user;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AboutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("(session) Initializing...");

        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("about.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("(session) Processing...");

        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));
        request.getRequestDispatcher("about.jsp").include(request, response);
    }

}
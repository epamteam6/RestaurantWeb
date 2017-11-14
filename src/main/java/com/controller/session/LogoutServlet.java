package com.controller.session;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("(session) Initializing...");

        //System.out.println(request.getSession().getAttribute("loggedInUser"));
        request.getSession().setAttribute("loggedInUser", null);
        request.getSession().invalidate();

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("index.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

}
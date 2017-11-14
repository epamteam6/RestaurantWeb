package com.controller.filters;

import com.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionSecurityFilter implements Filter {

    private static final Logger log = Logger.getLogger(SessionSecurityFilter.class);

    private UserService userService = UserService.getInstance();

    /**
     * SessionSecurityFilter does not allow to register or log in until previous session finished (logged out)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // for details look at doPost method in LoginServlet & LogoutServlet
        Object o = req.getSession().getAttribute("loggedInUser");
        if (o != null) {
            String username = (String) o;
            boolean logged = userService.getUserByName(username).isPresent();

            if (logged) {
                //res.sendRedirect("session_error.jsp");
                //return;

                req.setAttribute("username", username);

                log.debug("Attempt to system entry during not closed session, by " + username);

                req.getRequestDispatcher("/session_error.jsp").forward(req, res);
            }
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }


}

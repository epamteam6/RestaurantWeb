package com.controller.filters;

import com.model.User;
import com.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RestrictionSecurityFilter implements Filter {

    private static final Logger log = Logger.getLogger(RestrictionSecurityFilter.class);

    private UserService userService = UserService.getInstance();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // for details look at doPost method in LoginServlet & LogoutServlet
        Object o = req.getSession().getAttribute("loggedInUser");
        if (o != null) {
            String username = (String) o;
            Optional<User> opt = userService.getUserByName(username);
            boolean isAdmin = false;
            if (opt.isPresent())
                isAdmin = opt.get().isAdmin();

            if (isAdmin) {
                chain.doFilter(request, response);
                return;
            }
        }

        log.debug("Forbidden attempt to use the control panel by " + (String) o);
        res.sendRedirect("/404.jsp");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

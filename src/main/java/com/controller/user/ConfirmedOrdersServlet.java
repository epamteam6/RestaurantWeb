package com.controller.user;

import com.controller.admin.ConfirmationServlet;
import com.model.Order;
import com.model.User;
import com.service.OrderService;
import com.service.OrderStatusService;
import com.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConfirmedOrdersServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ConfirmationServlet.class);

    private UserService userService = UserService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private OrderStatusService orderStatusService = OrderStatusService.getInstance();
    private List<List> usersOrders;
    private List<Long> orderNumbers;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("(user) Initializing...");

        usersOrders = new ArrayList<>();
        orderNumbers = new ArrayList<>();

        String username = (String) request.getSession().getAttribute("loggedInUser");
        Optional<User> optional = userService.getUserByName(username);

        if (!optional.isPresent()) {
            response.sendRedirect("user_create_order.jsp");
            return;
        }

        User user = optional.get();
        Map<Long, Map<String, Long>> ordersDetails = orderService.orderDetails(user.getUserName(), Order.Status.CONFIRMED);
        if (!ordersDetails.isEmpty()) {
            for (Long number : ordersDetails.keySet()) {
                orderNumbers.add(number);
                List details = new ArrayList();
                details.add(number);
                details.add(ordersDetails.get(number));
                details.add(orderService.getOrderDAO().getById(number).get().getTotalSum());
                usersOrders.add(details);
            }
        }

        request.setAttribute("usersOrders", usersOrders);
        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("user_confirmed_orders.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("(user) Processing...");

        request.getRequestDispatcher("user_confirmed_orders.jsp").include(request, response);
    }
}
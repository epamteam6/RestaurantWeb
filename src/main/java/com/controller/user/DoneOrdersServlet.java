package com.controller.user;

import com.model.Order;
import com.model.User;
import com.service.OrderService;
import com.service.OrderStatusService;
import com.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class DoneOrdersServlet extends HttpServlet {

    private Map<String, Map<String, Long>> menu;
    private UserService userService = UserService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private OrderStatusService orderStatusService = OrderStatusService.getInstance();
    private Map<String, Map<Long, Map<String, Long>>> usersOrders;
    private List<Long> orderNumbers;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> allUsers = userService.getUserDAO().getAll();
        System.out.println(allUsers);

        usersOrders = new HashMap<>();
        orderNumbers = new ArrayList<>();

        String username = (String) request.getSession().getAttribute("loggedInUser");
        Optional<User> optional = userService.getUserByName(username);

        if (!optional.isPresent())
        {
            response.sendRedirect("user_create_order.jsp");
            return;
        }

        User user = optional.get();
        Map<Long, Map<String, Long>> ordersDetails = orderService.orderDetails(user.getUserName(), Order.Status.PAID);
        if (!ordersDetails.isEmpty()) {
            for (Long number : ordersDetails.keySet()) {
                orderNumbers.add(number);
            }
            usersOrders.put(user.getUserName(), ordersDetails);
        }

        request.setAttribute("usersOrders", usersOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user_done_orders.jsp");

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("user_done_orders.jsp").include(request, response);
    }
}
package com.controller.admin;

import com.dao.*;
import com.model.Order;
import com.model.User;
import com.mysql.jdbc.Driver;
import com.service.MenuService;
import com.service.OrderService;
import com.service.OrderStatusService;
import com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ConfirmationServlet.class);

    private UserService userService = UserService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private OrderStatusService orderStatusService = OrderStatusService.getInstance();
    private List<List> usersOrders;
    private List<Long> orderNumbers;
    private List<User> allUsers;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("Initializing...");

        allUsers = userService.getUserDAO().getAll();
        System.out.println(allUsers);

        getCreatedOrders();

        System.out.println(usersOrders);
        request.setAttribute("usersOrders", usersOrders);
        request.setAttribute("orderNumbers", orderNumbers);
        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_confirmation.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Processing...");

        request.getRequestDispatcher("admin_confirmation.jsp").include(request, response);


        System.out.println(orderNumbers);

        Boolean isConfirmButtonClicked = request.getParameter("Confirm") != null;
        Boolean isCancelButtonClicked = request.getParameter("Cancel") != null;

        Boolean isAnyOptionChosen = false;
        for (Long number : orderNumbers) {
            Boolean checked = request.getParameter(number.toString()) != null;
            if (checked) {
                isAnyOptionChosen = true;
                if (isConfirmButtonClicked) {
                    orderStatusService.confirmOrder(number);

                }
                if (isCancelButtonClicked) {
                    orderService.cancelOrder(number);
                }
            }
        }

        getCreatedOrders();
        request.setAttribute("usersOrders", usersOrders);
        request.setAttribute("orderNumbers", orderNumbers);
        request.setAttribute("username", (String) request.getSession().getAttribute("loggedInUser"));

        if(!isAnyOptionChosen){
            request.setAttribute("message", "You didn't choose any orders!");
        }

        else if (isConfirmButtonClicked) {
            request.setAttribute("message", "You confirmed selected orders!");
        }

        else if (isCancelButtonClicked) {
            request.setAttribute("message", "You canceled selected orders!");
        }

        getServletContext().getRequestDispatcher("/admin_confirmation.jsp").forward(request, response);


    }

    private void getCreatedOrders() {
        usersOrders = new ArrayList<>();
        orderNumbers = new ArrayList<>();

        for (User user : allUsers) {
            Map<Long, Map<String, Long>> ordersDetails = orderService.orderDetails(user.getUserName(), Order.Status.CREATED);
            if (!ordersDetails.isEmpty()) {
                for (Long number : ordersDetails.keySet()) {
                    orderNumbers.add(number);
                    List details = new ArrayList();
                    details.add(user.getUserName());
                    details.add(number);
                    details.add(ordersDetails.get(number));
                    details.add(orderService.getOrderDAO().getById(number).get().getTotalSum());
                    usersOrders.add(details);
                }
            }
        }
    }
}
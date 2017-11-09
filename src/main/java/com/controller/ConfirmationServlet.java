package com.controller;

import com.dao.*;
import com.model.Order;
import com.model.User;
import com.mysql.jdbc.Driver;
import com.service.MenuService;
import com.service.OrderService;
import com.service.OrderStatusService;
import com.service.UserService;
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

    private Map<String, Map<String, Long>> menu;
    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private UserService userService;
    private SimpleDriverDataSource dataSource;
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DishTypeDAO dishTypeDAO;
    private DishDAO dishDAO;
    private DishOrderDAO dishOrderDAO;
    private Map<String, Map<Long, Map<String, Long>>> usersOrders;
    private List<Long> orderNumbers;

    {
        try { //we're planining to make connection pool instead of this
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");

            dishDAO = DishDAO.getInstance();
            dishDAO.setDataSource(dataSource);
            dishOrderDAO = DishOrderDAO.getInstance();
            dishOrderDAO.setDataSource(dataSource);
            orderDAO = OrderDAO.getInstance();
            orderDAO.setDataSource(dataSource);
            userDAO = UserDAO.getInstance();
            userDAO.setDataSource(dataSource);
            orderDAO = OrderDAO.getInstance();
            orderDAO.setDataSource(dataSource);

            userService = UserService.getInstance();
            userService.setUserDAO(userDAO);

            orderService = OrderService.getInstance();
            orderService.setDishDAO(dishDAO);
            orderService.setDishOrderDAO(dishOrderDAO);
            orderService.setOrderDAO(orderDAO);
            orderService.setUserDAO(userDAO);

            orderStatusService = OrderStatusService.getInstance();
            orderStatusService.setOrderDAO(orderDAO);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> allUsers = userDAO.getAll();
        System.out.println(allUsers);

        usersOrders = new HashMap<>();
        orderNumbers = new ArrayList<>();

        for (User user : allUsers) {
            Map<Long, Map<String, Long>> ordersDetails = orderService.orderDetails(user.getUserName(), Order.Status.CREATED);
            if (!ordersDetails.isEmpty()) {
                for (Long number : ordersDetails.keySet()) {
                    orderNumbers.add(number);
                }
                usersOrders.put(user.getUserName(), ordersDetails);
            }
        }

        request.setAttribute("usersOrders", usersOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("make_order.jsp").include(request, response);
        Map<String, Long> dishNamesAndAmount = new HashMap<>();
        System.out.println(orderNumbers);
        for (Long number : orderNumbers) {
            Boolean checked = request.getParameter(number.toString()) != null;
            if (checked) {
                orderStatusService.confirmOrder(number);
            }
        }


        response.sendRedirect("/success.jsp");

    }
}
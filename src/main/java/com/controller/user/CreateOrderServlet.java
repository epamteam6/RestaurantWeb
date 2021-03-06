package com.controller.user;

import com.service.MenuService;
import com.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateOrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateOrderServlet.class);

    private Map<String, Map<String, Long>> menu;
    private MenuService menuService = MenuService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private String userName = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("(user) Initializing...");

        userName = (String) request.getSession().getAttribute("loggedInUser");

        menu = menuService.getMenu();


        request.setAttribute("menu", menu);
        request.setAttribute("username", userName);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("user_create_order.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("(user) Processing...");

        request.getRequestDispatcher("user_create_order.jsp").include(request, response);
        Map<String, Long> dishNamesAndAmount = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> pair : menu.entrySet()) {
            for (Map.Entry<String, Long> subPair : pair.getValue().entrySet()) {
                String number = request.getParameter(subPair.getKey());
                if (number.equals("")) {
                    continue;
                }
                Long amount = Long.parseLong(number);
                if (amount > 0) {
                    dishNamesAndAmount.put(subPair.getKey(), amount);
//                    System.out.println(subPair.getKey() + " " + amount);
                }
            }

        }

        if (!dishNamesAndAmount.isEmpty()) {
            orderService.makeOrder(userName, dishNamesAndAmount);
            response.sendRedirect("user_created_orders");
        } else {
            request.setAttribute("message", "You didn't choose any dishes!");
            request.setAttribute("menu", menu);
            request.setAttribute("username", userName);

            request.getRequestDispatcher("user_create_order.jsp").forward(request, response);
        }

    }
}
package com.controller.user;

import com.dao.*;
import com.mysql.jdbc.Driver;
import com.service.MenuService;
import com.service.OrderService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CreateOrderServlet extends HttpServlet {

    private Map<String, Map<String, Long>> menu;
    private MenuService menuService = MenuService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private String userName = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();


        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                userName = cookies[i].getValue();
                System.out.println(userName);
            }
        }

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
                    System.out.println(subPair.getKey() + " " + amount);
                }
            }

        }

        if (!dishNamesAndAmount.isEmpty()) {
            orderService.makeOrder(userName, dishNamesAndAmount);
            response.sendRedirect("success.jsp");
        } else {
            request.setAttribute("message", "You didn't choose any dishes!");
            request.setAttribute("menu", menu);
            request.setAttribute("username", userName);

            request.getRequestDispatcher("user_create_order").forward(request, response);
        }

    }
}
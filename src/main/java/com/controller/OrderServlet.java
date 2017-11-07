package com.controller;

import com.dao.*;
import com.mysql.jdbc.Driver;
import com.service.MenuService;
import com.service.OrderService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderServlet extends HttpServlet {

    private Map<String, Map<String, Long>> menu;
    private MenuService menuService;
    private OrderService orderService;
    private SimpleDriverDataSource dataSource;
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DishTypeDAO dishTypeDAO;
    private DishDAO dishDAO;
    private DishOrderDAO dishOrderDAO;

    {
      try {
          SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                  "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");

          dishDAO = DishDAO.getInstance();
          userDAO = UserDAO.getInstance();
          orderDAO = OrderDAO.getInstance();
          dishOrderDAO = DishOrderDAO.getInstance();
          dishTypeDAO = DishTypeDAO.getInstance();

          userDAO.setDataSource(dataSource);
          orderDAO.setDataSource(dataSource);
          dishDAO.setDataSource(dataSource);
          dishOrderDAO.setDataSource(dataSource);
          dishTypeDAO.setDataSource(dataSource);

          menuService = MenuService.getInstance();
          orderService = OrderService.getInstance();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        menu = menuService.getMenu();


        request.setAttribute("menu", menu);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/make_order.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("make_order.jsp").include(request, response);
        Map<String, Long> dishNamesAndAmount = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> pair : menu.entrySet())
        {
            for (Map.Entry<String, Long> subPair : pair.getValue().entrySet()){
                Long amount = Long.parseLong(request.getParameter(subPair.getKey()));
                if (amount>0){
                    dishNamesAndAmount.put(subPair.getKey(), amount);
                    System.out.println(subPair.getKey()+" "+amount);
                }
            }

        }


        orderService.makeOrder("Petrov", dishNamesAndAmount);
        response.sendRedirect("/success.jsp");

    }
}
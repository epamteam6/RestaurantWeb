package com.controller;

import com.dao.*;
import com.mysql.jdbc.Driver;
import com.service.MenuService;
import com.service.OrderService;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    private MenuService menuService;
    private DishTypeDAO dishTypeDAO;
    private DishDAO dishDAO;
    private Map<String, Map<String, Long>> menu;
    private OrderService orderService;
    private SimpleDriverDataSource dataSource;
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DishOrderDAO dishOrderDAO;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        menuService = MenuService.getInstance();
        dishTypeDAO = DishTypeDAO.getInstance();
        dishDAO = DishDAO.getInstance();

        try {
            dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");
            dishDAO.setDataSource(dataSource);
            dishTypeDAO.setDataSource(dataSource);
            menuService.setDishTypeDAO(dishTypeDAO);
            menuService.setDishDAO(dishDAO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu = menuService.getMenu();


        request.setAttribute("menu", menu);

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/index.jsp");
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").include(request, response);
        //Map<String, Long> dishNamesAndAmount = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> pair : menu.entrySet())
        {
            for (Map.Entry<String, Long> subPair : pair.getValue().entrySet()){
                String amount = request.getParameter(subPair.getKey());
                if (Long.parseLong(amount)>0){
                    //dishNamesAndAmount.put(subPair.getKey(), subPair.getValue());
                    System.out.println(subPair.getKey()+" "+amount);
                }
            }

        }

/*
        orderService = OrderService.getInstance();
        userDAO = UserDAO.getInstance();
        orderDAO = OrderDAO.getInstance();
        dishOrderDAO = DishOrderDAO.getInstance();

        orderService.setDishDAO(dishDAO);
        orderService.setDishOrderDAO(dishOrderDAO);
        orderService.setOrderDAO(orderDAO);
        orderService.setUserDAO(userDAO);
        orderService.makeOrder("Petrov", dishNamesAndAmount);*/
        response.sendRedirect("/madeOrder.jsp");

    }
}
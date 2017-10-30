package com.dao;

import com.model.Order;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDAOTest {

    private OrderDAO orderDAO;

    @Before
    public void init(){
        orderDAO = new OrderDAO(getConnection());
    }

    @Test
    public void cancelOrder() throws Exception {
        orderDAO.cancelOrder(6);
    }

    @Test
    public void updateOrder() throws Exception {
        orderDAO.updateOrder(new Order(5,2, LocalDateTime.now(), 3005, Order.status.CONFIRMED));
    }


    @Test
    public void getAllOrders() throws Exception {
        List<Order> allOrders = orderDAO.getAllOrders();
        allOrders.forEach(System.out::println);
    }

    @Test
    public void getOrderById() throws Exception {
        System.out.println(orderDAO.getOrderById(9));
    }

    private Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food?serverTimezone=UTC","root", "root");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }


    @Test
    public void createOrder() throws Exception {
        orderDAO.createOrder(new Order(6,2, LocalDateTime.now(), 505, Order.status.CREATED));

    }

}
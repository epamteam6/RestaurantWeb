package com.dao;

import com.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderDAOTest {

    private OrderDAO orderDAO;

    @Before
    public void init(){
        orderDAO = new OrderDAO(getDataSource());
    }

    @Test
    public void cancelOrder() throws Exception {
        orderDAO.cancel(3);
    }

    @Test
    public void updateOrder() throws Exception {
        Order toAdd = new Order(4,2, LocalDateTime.now(), 7005, Order.status.CONFIRMED);
        System.out.println(orderDAO.update(toAdd));
        //assertEquals(toAdd, orderDAO.getById(4));

    }


    @Test
    public void getAllOrders() throws Exception {
        List<Order> actual = orderDAO.getAll();

        List<Order> expected = Arrays.asList(
                new Order(1,1,LocalDateTime.of(2017,10,25,13,54,19),475,Order.status.CREATED),
                new Order(2,2,LocalDateTime.of(2017,10,25,13,54,19),290,Order.status.CONFIRMED),
                new Order(3,4,LocalDateTime.of(2017,10,25,13,54,19),330,Order.status.PAID)
        );

        assertThat(actual, is(expected));

    }

    @Test
    public void getOrderById() throws Exception {
        System.out.println(orderDAO.getById(9));
    }

    /*private Connection getConnection() {
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

    }*/


    @Test
    public void createOrder() throws Exception {
        Order toAdd = new Order(4,2, LocalDateTime.now(), 7005, Order.status.CONFIRMED);
        System.out.println(orderDAO.update(toAdd));
        assertEquals(toAdd, orderDAO.getById(4));


    }

    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("data.sql")
                .build();

        return db;
    }

}
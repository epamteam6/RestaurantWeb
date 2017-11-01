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

    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Before
    public void init() {
        orderDAO.setDataSource(getDataSource());
    }


    @Test
    public void getAllOrders() throws Exception {
        List<Order> actual = orderDAO.getAll();

        List<Order> expected = Arrays.asList(
                new Order(1, 1,
                        LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                        475, Order.Status.CREATED),
                new Order(2, 2,
                        LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                        290, Order.Status.CONFIRMED),
                new Order(3, 4,
                        LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                        330, Order.Status.PAID)
        );

        assertThat(actual, is(expected));

    }

    @Test
    public void getOrderById() throws Exception {

        Order actual = orderDAO.getById(1).get();

        Order expected = new Order(1, 1, LocalDateTime.of(2017, 10, 25,
                13, 54, 19), 475, Order.Status.CREATED);

        assertEquals(actual, expected);
    }

    @Test
    public void createOrder() throws Exception {
        Order toAdd = new Order(4, 2, LocalDateTime.now(), 7005, Order.Status.CONFIRMED);
        orderDAO.create(toAdd);
        assertEquals(toAdd, orderDAO.getById(4).get());


    }

    @Test
    public void updateOrder() throws Exception {
        Order toUpdate = new Order(2, 2,
                LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                690, Order.Status.CONFIRMED);
        orderDAO.update(toUpdate);
        assertEquals(toUpdate, orderDAO.getById(2).get());

    }

    @Test
    public void cancelOrder() throws Exception {
        orderDAO.cancel(3);
        assertEquals(false, orderDAO.getById(3).isPresent());
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
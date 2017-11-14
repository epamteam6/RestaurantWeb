package com.integrational_dao;

import com.dao.OrderDAO;
import com.model.Order;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class IntegroOrderDAO {

    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Test
    public void getOrderById() {

        Order actual = orderDAO.getById(1).get();

        Order expected = new Order(1, 1, LocalDateTime.of(2017, 10, 25,
                19, 54, 19), 475, Order.Status.CREATED);

        assertEquals(actual, expected);
    }

    @Test
    public void getByUserAndStatus() throws Exception {

        List<Order> expected = Arrays.asList(
                new Order(1, 1,
                        LocalDateTime.of(2017, 10, 25, 19, 54, 19),
                        475, Order.Status.CREATED)
        );

        List<Order> actual = orderDAO.getByUserAndStatus(1, Order.Status.CREATED);

        assertThat(actual, is(expected));
    }

    // getAll

    // create

    // update

    // cancel
}

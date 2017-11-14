package com.integrational_dao;

import com.dao.DishOrderDAO;
import com.model.DishOrder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntegroDishOrderDAO {

    private DishOrderDAO dishOrderDAO = DishOrderDAO.getInstance();

    @Test
    public void getByOrderId() {

        List<DishOrder> act = dishOrderDAO.getByOrderId(1);
        DishOrder dishOrder1 = new DishOrder(1, 1, 2, 2, 340);
        DishOrder dishOrder2 = new DishOrder(2, 1, 6, 1, 135);
        List<DishOrder> exp = Arrays.asList(dishOrder1, dishOrder2);

        assertThat(act, is(exp));
    }

    @Test
    public void getById() {

        DishOrder act = dishOrderDAO.getById(2).get();
        DishOrder exp = new DishOrder(2, 1, 6, 1, 135);

        assertThat(act, is(exp));
    }

    // add

    // update

    // remove

    // getAll
}

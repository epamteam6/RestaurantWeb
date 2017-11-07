package com.dao;

import com.model.DishOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DishOrderDAOTest {

    DishOrderDAO dishOrderDAO = DishOrderDAO.getInstance();

    @Before
    public void init() throws Exception {

        dishOrderDAO.setDataSource(getDataSource());
    }

    public DataSource getDataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init_dish_order.sql")
                .addScript("data_dish_order.sql")
                .build();
    }

    @Test
    public void add() throws Exception {

        DishOrder dishOrder = new DishOrder(6, 100, 100, 100, 1000);
        List<DishOrder> exp = Arrays.asList(dishOrder);
        dishOrderDAO.create(dishOrder);

        List<DishOrder> act = dishOrderDAO.getByOrderId(100);
        assertEquals(exp, act);

        List<DishOrder> act2 = dishOrderDAO.getByOrderId(1000);
        assertFalse(act2.size() != 0);
    }

    @Test
    public void update() throws Exception {

        DishOrder act = dishOrderDAO.getById(1).get();
        act.setDishSum(100);
        dishOrderDAO.update(act);

        DishOrder exp = dishOrderDAO.getById(1).get();
        assertEquals(act, exp);
    }

    @Test
    public void remove() throws Exception {

        assertTrue(dishOrderDAO.getById(2).isPresent());
        dishOrderDAO.remove(2);
        assertFalse(dishOrderDAO.getById(2).isPresent());
    }

    @Test
    public void getAll() throws Exception {

        List<DishOrder> act = dishOrderDAO.getAll();

        List<DishOrder> exp = new ArrayList<>();
        exp.add(new DishOrder(1, 1, 2, 2, 340));
        exp.add(new DishOrder(2, 1, 6, 1, 135));
        exp.add(new DishOrder(3, 2, 4, 1, 180));
        exp.add(new DishOrder(4, 2, 9, 2, 110));
        exp.add(new DishOrder(5, 3, 8, 3, 330));

        assertThat(act, is(exp));

        List<DishOrder> act2 = dishOrderDAO.getAll();

        List<DishOrder> exp2 = new ArrayList<>();
        exp.add(new DishOrder(1, 1, 2, 2, 340));
        exp.add(new DishOrder(2, 1, 6, 1, 135));
        exp.add(new DishOrder(3, 2, 4, 1, 180));
        exp.add(new DishOrder(4, 2, 9, 2, 110));
        exp.add(new DishOrder(1000, 3, 8, 3, 330));

        assertThat(act2, is(not(exp2)));
    }

    @Test
    public void getByOrderId() throws Exception {

        List<DishOrder> act = dishOrderDAO.getByOrderId(1);
        DishOrder dishOrder1 = new DishOrder(1, 1, 2, 2, 340);
        DishOrder dishOrder2 = new DishOrder(2, 1, 6, 1, 135);
        List<DishOrder> exp = Arrays.asList(dishOrder1, dishOrder2);

        assertThat(act, is(exp));
    }

    @Test
    public void getById() throws Exception {

        DishOrder act = dishOrderDAO.getById(2).get();
        DishOrder exp = new DishOrder(2, 1, 6, 1, 135);

        assertThat(act, is(exp));
    }
}

package com.dao;

import com.model.DishOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.sql.DataSource;

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
    public void getByOrderId() throws Exception {

        DishOrder act = dishOrderDAO.getByOrderId(1);
        DishOrder exp = new DishOrder(1, 1, 2, 2, 340);

        assertThat(act, is(exp));
    }



}

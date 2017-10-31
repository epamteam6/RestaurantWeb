package com.dao;

import org.junit.Before;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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
}

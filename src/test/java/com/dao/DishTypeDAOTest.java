package com.dao;

import com.model.DishType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DishTypeDAOTest {

    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();


    @Before
    public void init() throws Exception {
        dishTypeDAO.setDataSource(getDataSource());
    }

    @Test
    public void getAll() throws Exception {
        List<DishType> actual = dishTypeDAO.getAll();

        List<DishType> expected = Arrays.asList(
                new DishType(1, "Soups"),
                new DishType(2, "Desserts"),
                new DishType(3, "Drinks")
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void getById() throws Exception {
        DishType actual = dishTypeDAO.getById(1).get();
        DishType expected = new DishType(1, "Soups");
        assertEquals(actual, expected);
    }

    @Test
    public void create() throws Exception {
        DishType toAdd = new DishType(4, "Salads");
        dishTypeDAO.create(toAdd);
        assertEquals(toAdd, dishTypeDAO.getById(4).get());
    }

    @Test
    public void update() throws Exception {
        DishType toUpdate = new DishType(1, "Salads");
        dishTypeDAO.update(toUpdate);
        assertEquals(toUpdate, dishTypeDAO.getById(1).get());
    }

    @Test
    public void delete() throws Exception {
        dishTypeDAO.remove(2);
        assertFalse(dishTypeDAO.getById(2).isPresent());
    }

    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("initDishType.sql")
                .addScript("dataDishType.sql")
                .build();
        return db;
    }
}
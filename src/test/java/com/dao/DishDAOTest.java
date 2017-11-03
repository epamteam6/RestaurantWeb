package com.dao;

import com.model.Dish;
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

public class DishDAOTest {


    private DishDAO dishDAO = DishDAO.getInstance();


    @Before
    public void init() throws Exception {
        dishDAO.setDataSource(getDataSource());
    }

    @Test
    public void getAll() throws Exception {
        List<Dish> actual = dishDAO.getAll();

        List<Dish> expected = Arrays.asList(
                new Dish(1, "Borsch", 1, 150),
                new Dish(2, "Kharcho", 1, 170),
                new Dish(3, "Solyanka", 1, 200),
                new Dish(4, "Olivie", 4, 180)
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void getById() throws Exception {
        Dish actual = dishDAO.getById(1).get();
        Dish expected = new Dish(1, "Borsch", 1, 150);
        assertEquals(actual, expected);
    }

    @Test
    public void create() throws Exception {
        Dish toAdd = new Dish(5, "Greek", 4, 220);
        dishDAO.create(toAdd);
        assertEquals(toAdd, dishDAO.getById(5).get());
    }

    @Test
    public void update() throws Exception {
        Dish toUpdate = new Dish(1, "Chiken Soup", 1, 200);
        dishDAO.update(toUpdate);
        assertEquals(toUpdate, dishDAO.getById(1).get());
    }

    @Test
    public void delete() throws Exception {
        dishDAO.remove(4);
        assertFalse(dishDAO.getById(4).isPresent());
    }

    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("initDish.sql")
                .addScript("dataDish.sql")
                .build();
        return db;
    }

    @Test
    public void getByName() throws Exception {
        Dish actual = dishDAO.getByName("Borsch").get();
        Dish expected = new Dish(1, "Borsch", 1, 150);
        assertEquals(actual, expected);
    }
}
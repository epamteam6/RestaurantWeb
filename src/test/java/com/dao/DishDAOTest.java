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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DishDAOTest {

    private DishDAO dishDAO = DishDAO.getInstance();


    @Before
    public void init() throws Exception {
        dishDAO.setDataSource(getDataSource());
    }

    @Test
    public void delete() throws Exception {
        dishDAO.delete(4);
        assertEquals(null, dishDAO.getById(4));
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


    /*

    @Test
    public void updateDish() throws Exception {
        dishDAO.updateDish(new Dish(10,"RedMilk",3, 80));
    }


    @Test
    public void getAllDishes() throws Exception {
        List<Dish> allDishes = dishDAO.getAllDishes();
        allDishes.forEach(System.out::println);
    }

*/
    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("initDish.sql")
                .addScript("dataDish.sql")
                .build();

        return db;
    }

}
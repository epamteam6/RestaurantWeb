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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DishTypeDAOTest {

    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();


    @Before
    public void init() throws Exception {
        dishTypeDAO.setDataSource(getDataSource());
    }

    @Test
    public void getAll() throws Exception {
        List<DishType> actual = DishTypeDAO.getAll();

        List<DishType> expected = Arrays.asList(
                new DishType(1, "Borsch"),
                new DishType(2, "Kharcho"),
                new DishType(3, "Solyanka")
        );
        assertThat(actual, is(expected));
    }

    @Test
    public void getById() throws Exception {
        DishType actual = dishTypeDAO.getById(1);
        DishType expected = new DishType(1, "Borsch");
        assertEquals(actual, expected);
    }

    @Test
    public void create() throws Exception {
        DishTypeDAO toAdd = new DishType(5, "Greek");
        DishTypeDAO.create(toAdd);
        assertEquals(toAdd, DishTypeDAO.getById(5));
    }

    @Test
    public void update() throws Exception {
        DishTypeDAO toUpdate = new DishTypeDAO(1, "Chiken Soup");
        DishTypeDAO.update(toUpdate);
        assertEquals(toUpdate, DishTypeDAO.getById(1));
    }

    @Test
    public void delete() throws Exception {
        DishTypeDAO.delete(4);
        assertEquals(null, DishTypeDAO.getById(4));
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
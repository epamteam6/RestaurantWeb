package com.integrational_dao;

import com.dao.DishTypeDAO;
import com.model.DishType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class IntegroDishTypeDAO {

    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();

    @Test
    public void getById() throws Exception {
        DishType actual = dishTypeDAO.getById(1).get();
        DishType expected = new DishType(1, "Soups");
        assertEquals(actual, expected);
    }

    @Test
    public void getAll() throws Exception {
        List<DishType> actual = dishTypeDAO.getAll();

        List<DishType> expected = Arrays.asList(
                new DishType(1, "Soups"),
                new DishType(2, "Desserts"),
                new DishType(3, "Drinks"),
                new DishType(4, "Salads")
        );
        assertThat(actual, is(expected));
    }

    // create

    // update

    // delete
}

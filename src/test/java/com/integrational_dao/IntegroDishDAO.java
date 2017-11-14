package com.integrational_dao;

import com.dao.DishDAO;
import com.model.Dish;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegroDishDAO {

    private DishDAO dishDAO = DishDAO.getInstance();

    @Test
    public void getByName() {

        Dish actual = dishDAO.getByName("Borsch").get();
        Dish expected = new Dish(1, "Borsch", 1, 150);
        assertEquals(actual, expected);
    }

    @Test
    public void getById() {
        Dish actual = dishDAO.getById(1).get();
        Dish expected = new Dish(1, "Borsch", 1, 150);
        assertEquals(actual, expected);
    }

    // getAll

    // create

    // update

    // delete
}

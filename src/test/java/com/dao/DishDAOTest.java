package com.dao;

import com.model.Dish;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DishDAOTest {

    private DishDAO dishDAO;

    @Before
    public void init() throws Exception {
        dishDAO = new DishDAO(getConnection());
    }


    @Test
    public void addDish() throws Exception {
        dishDAO = new DishDAO(getConnection());
        dishDAO.addDish(new Dish(12, "Сhicken Soup", 1, 200));
        dishDAO.addDish(new Dish(13, "Tea", 3, 50));

    }

    @Test
    public void updateDish() throws Exception {
        dishDAO.updateDish(new Dish(10,"RedMilk",3, 80));
    }

    @Test
    public void deleteDish() throws Exception {
        dishDAO.deleteDish(12);
    }

    @Test
    public void getAllDishes() throws Exception {
        List<Dish> allDishes = dishDAO.getAllDishes();
        allDishes.forEach(System.out::println);
    }

    private Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a208000_food?serverTimezone=UTC", "a208000_root", "foodroot");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

}
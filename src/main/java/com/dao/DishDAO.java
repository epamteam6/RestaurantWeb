package com.dao;

import com.model.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DishDAO implements RestrauntDao {

    private Connection connection;

    public DishDAO(Connection statement) {
        this.connection = statement;
    }


    @Override
    public List<Dish> getAllDishes() {

        List<Dish> allDishes = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM Dishes");
            while (rs.next()) {
                Dish dish = new Dish(rs.getInt("id"),
                        rs.getString("dish"),
                        rs.getLong("dish_type_id"),
                        rs.getInt("price"));

                allDishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDishes;
    }

    @Override
    public void addDish(Dish dish) {
        try {
            final PreparedStatement sql = connection.prepareStatement("INSERT INTO Dishes(id, dish, dishTypeId, price)  values (?,?,?,?)");
            sql.setLong(1, dish.getId());
            sql.setString(2, dish.getDish().toString());
            sql.setLong(3, dish.getDishTypeId());
            sql.setLong(4, dish.getPrice());
            sql.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void updateDish(Dish dish) {
        try {
            final PreparedStatement sql = connection.prepareStatement("UPDATE Dishes SET dish=?, dishTypeId=?, price=? where id=?");
            sql.setString(1, dish.getDish().toString());
            sql.setLong(2, dish.getDishTypeId());
            sql.setLong(3, dish.getPrice());
            sql.setLong(4, dish.getId());
            sql.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deleteDish(long id) {
        try {
            final PreparedStatement sql = connection.prepareStatement("DELETE from Dishes where id=?");
            sql.setLong(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





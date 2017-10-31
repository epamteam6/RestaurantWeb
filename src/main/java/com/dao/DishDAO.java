package com.dao;

import com.model.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DishDAO {

    private static final String UPDATE_QUERY = "UPDATE Dishes SET dish=?, dishTypeId=?, price=? where id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dishes(id, dish, dishTypeId, price)  values (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM Dishes WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Dishes WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Dishes";

    private DataSource dataSource;
    private static DishDAO instance;

    private DishDAO() {
    }

    public static DishDAO getInstance() {
        if (instance == null) {
            instance = new DishDAO();
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean create(Dish dish) {
        boolean isCreated = false;
        try (Connection connection = dataSource.getConnection()) {

            final PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dish.getId());
            sql.setString(2, dish.getDish().toString());
            sql.setLong(3, dish.getDishTypeId());
            sql.setLong(4, dish.getPrice());
            sql.executeUpdate();
            isCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreated;
    }

    public List<Dish> getAll() {
        List<Dish> res = new ArrayList<>();
        Statement statement;
        try (Connection connection = dataSource.getConnection()) {
            statement = connection.createStatement();

            final ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createDish(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private Dish createDish(ResultSet rs) throws SQLException {
        return new Dish(
                rs.getInt("id"),
                rs.getString("dish"),
                rs.getLong("dish_type_id"),
                rs.getInt("price"));
    }

    public boolean update(Dish dish) {
        boolean isUpdated = false;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dish.getDish().toString());
            sql.setLong(2, dish.getDishTypeId());
            sql.setLong(3, dish.getPrice());
            sql.setLong(4, dish.getId());
            sql.executeUpdate();
            isUpdated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean delete(long id) {
        boolean isCanceled = false;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setLong(1, id);
            sql.executeUpdate();
            isCanceled = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCanceled;
    }
}





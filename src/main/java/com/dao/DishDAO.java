package com.dao;

import com.model.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DishDAO {

    private static final String UPDATE_QUERY = "UPDATE Dishes SET dish=?, dish_type_id=?, price=? where id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dishes(id, dish, dish_type_id, price)  values (?,?,?,?)";
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
        try (Connection connection = dataSource.getConnection()) {

            final PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dish.getId());
            sql.setString(2, dish.getDish());
            sql.setLong(3, dish.getDishTypeId());
            sql.setLong(4, dish.getPrice());
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Dish> getAll() {
        List<Dish> res = new ArrayList<>();
        Statement statement;
        try (Connection connection = dataSource.getConnection()) {
            statement = connection.createStatement();

            final ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(parseDish(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private Dish parseDish(ResultSet rs) throws SQLException {
        return new Dish(
                rs.getLong("id"),
                rs.getString("dish"),
                rs.getLong("dish_type_id"),
                rs.getLong("price"));
    }

    public boolean update(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dish.getDish().toString());
            sql.setLong(2, dish.getDishTypeId());
            sql.setLong(3, dish.getPrice());
            sql.setLong(4, dish.getId());
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public Dish getById(long id) {
        Dish dish = null;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            final ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dish = parseDish(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }
}





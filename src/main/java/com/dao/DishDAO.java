package com.dao;

import com.model.Dish;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishDAO implements RegularDAO<Dish> {

    private DataSource dataSource;
    private static DishDAO instance;

    private static final String UPDATE_QUERY = "UPDATE Dishes SET dish=?, dish_type_id=?, price=? WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dishes(id, dish, dish_type_id, price)  VALUES (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM Dishes WHERE id = ?";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM Dishes WHERE dish = ?";
    private static final String DELETE_QUERY = "DELETE FROM Dishes WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Dishes";

    private DishDAO() {
    }

    public static DishDAO getInstance() {
        if (instance == null) {
            instance = new DishDAO();
        }
        return instance;
    }


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Dish> getById(long id) {
        Optional<Dish> dish = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dish = Optional.of(createDishEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> res = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createDishEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public boolean create(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
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

    @Override
    public boolean remove(long id) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setLong(1, id);
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Dish dish) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dish.getDish());
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


    private Dish createDishEntity(ResultSet rs) throws SQLException {
        return new Dish(
                rs.getLong("id"),
                rs.getString("dish"),
                rs.getLong("dish_type_id"),
                rs.getLong("price"));
    }

    public Optional<Dish> getByName(String name) {
        Optional<Dish> dish = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(SELECT_BY_NAME_QUERY);
            sql.setString(1, name);

            final ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dish = Optional.of(parseDish(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }
}




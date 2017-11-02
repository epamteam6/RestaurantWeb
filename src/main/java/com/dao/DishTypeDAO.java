package com.dao;

import com.model.DishType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishTypeDAO implements RegularDAO<DishType> {

    private DataSource dataSource;
    private static DishTypeDAO instance;

    private static final String UPDATE_QUERY = "UPDATE Dish_Types SET dish_type=? WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dish_Types(id, dish_type)  VALUES (?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM Dish_Types WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Dish_Types WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Dish_Types";

    private DishTypeDAO() {
    }

    public static DishTypeDAO getInstance() {
        if (instance == null) {
            instance = new DishTypeDAO();
        }
        return instance;
    }


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<DishType> getById(long id) {

        Optional<DishType> dishType = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dishType = Optional.of(createDishTypeEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishType;
    }

    @Override
    public List<DishType> getAll() {
        List<DishType> res = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createDishTypeEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public boolean create(DishType dishType) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dishType.getId());
            sql.setString(2, dishType.getDishType());
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
    public boolean update(DishType dishType) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dishType.getDishType());
            sql.setLong(2, dishType.getId());
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private DishType createDishTypeEntity(ResultSet rs) throws SQLException {
        return new DishType(
                rs.getLong("id"),
                rs.getString("dish_type"));
    }
}





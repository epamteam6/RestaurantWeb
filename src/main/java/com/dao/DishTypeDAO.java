package com.dao;

import com.model.DishType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DishTypeDAO {

    private static final String UPDATE_QUERY = "UPDATE Dish_Types SET dish_type=?, where id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dish_Types(id, dish_type)  values (?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM Dish_Types WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Dish_Types WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Dish_Types";

    private DataSource dataSource;
    private static DishTypeDAO instance;

    private DishTypeDAO() {
    }

    public static DishTypeDAO getInstance() {
        if (instance == null) {
            instance = new DishTypeDAO();
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean create(DishType dishType) {
        boolean isCreated = false;
        try (Connection connection = dataSource.getConnection()) {

            final PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dishType.getId());
            sql.setString(2, dishType.getDishType().toString());
            sql.executeUpdate();
            isCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreated;
    }

    public List<DishType> getAll() {
        List<DishType> res = new ArrayList<>();
        Statement statement;
        try (Connection connection = dataSource.getConnection()) {
            statement = connection.createStatement();

            final ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createDishType(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private DishType createDishType(ResultSet rs) throws SQLException {
        return new DishType(
                rs.getLong("id"),
                rs.getString("dish"));
    }

    public boolean update(DishType dishType) {
        boolean isUpdated = false;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dishType.getDishType().toString());
            sql.setLong(2, dishType.getId());
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

    public DishType getById(long id) {
        DishType dishType = null;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            final ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dishType = createDishType(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishType;
    }
}





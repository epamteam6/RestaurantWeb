package com.dao;

import com.connectionpool.ConnectionPoolManager;
import com.model.Dish;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishDAO implements RegularDAO<Dish> {

    private static final Logger log = Logger.getLogger(DishDAO.class);

    private boolean isTestMode = false;
    private DataSource dataSource;

    private ConnectionPoolManager connectionPool = new ConnectionPoolManager();
    private Connection connection;

    private static DishDAO instance;

    private static final String UPDATE_QUERY = "UPDATE Dishes SET dishname=?, dish_type_id=?, price=? WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO Dishes(id, dishname, dish_type_id, price)  VALUES (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM Dishes WHERE id = ?";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM Dishes WHERE dishname = ?";
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

    // For tests only
    @Override
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;

        if (isTestMode && dataSource != null) {

            try {
                this.connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // For tests only
    public void setTestMode(boolean testMode) {

        if (dataSource != null && testMode) {

            try {
                this.connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        isTestMode = testMode;
    }

    @Override
    public Optional<Dish> getById(long id) {

        log.info("getById() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        Optional<Dish> dish = Optional.empty();
        try {

            PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                dish = Optional.of(createDishEntity(rs));

        } catch (SQLException e) {

            e.printStackTrace();
            log.error("getById() " + id);
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return dish;
    }

    @Override
    public List<Dish> getAll() {

        log.info("getAll()");

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<Dish> res = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);

            while (rs.next()) {
                res.add(createDishEntity(rs));
            }

        } catch (SQLException e) {

            log.error("getAll()");
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return res;
    }


    @Override
    public boolean create(Dish dish) {

        log.info("create() " + dish.getDishname());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dish.getId());
            sql.setString(2, dish.getDishname());
            sql.setLong(3, dish.getDishTypeId());
            sql.setLong(4, dish.getPrice());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("create() " + dish.getDishname());
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    @Override
    public boolean remove(long id) {

        log.info("remove() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setLong(1, id);
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("remove() " + id);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    @Override
    public boolean update(Dish dish) {

        log.info("update() " + dish.getDishname());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dish.getDishname());
            sql.setLong(2, dish.getDishTypeId());
            sql.setLong(3, dish.getPrice());
            sql.setLong(4, dish.getId());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("update() " + dish.getDishname());
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }


    private Dish createDishEntity(ResultSet rs) throws SQLException {
        return new Dish(
                rs.getLong("id"),
                rs.getString("dishname"),
                rs.getLong("dish_type_id"),
                rs.getLong("price"));
    }

    public Optional<Dish> getByName(String name) {

        log.info("getByName() " + name);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        Optional<Dish> dish = Optional.empty();
        try {

            PreparedStatement sql = connection.prepareStatement(SELECT_BY_NAME_QUERY);
            sql.setString(1, name);

            ResultSet rs = sql.executeQuery();
            if (rs.next())
                dish = Optional.of(createDishEntity(rs));

        } catch (SQLException e) {

            log.error("getByName() " + name);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return dish;
    }
}





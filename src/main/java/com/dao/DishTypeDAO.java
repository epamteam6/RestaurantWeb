package com.dao;

import com.connectionpool.ConnectionPoolManager;
import com.model.DishType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishTypeDAO implements RegularDAO<DishType> {

    private static final Logger log = Logger.getLogger(DishTypeDAO.class);

    private boolean isTestMode = false;
    private DataSource dataSource;

    private ConnectionPoolManager connectionPool = new ConnectionPoolManager();
    private Connection connection;

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
    public Optional<DishType> getById(long id) {

        log.info("getById() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        Optional<DishType> dishType = Optional.empty();
        try {
            PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                dishType = Optional.of(createDishTypeEntity(rs));
            }

        } catch (SQLException e) {

            log.error("getById() " + id);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return dishType;
    }

    @Override
    public List<DishType> getAll() {

        log.info("getAll()");

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<DishType> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createDishTypeEntity(rs));
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
    public boolean create(DishType dishType) {

        log.info("create() " + dishType.getDishType());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dishType.getId());
            sql.setString(2, dishType.getDishType());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("create() " + dishType.getDishType());
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
    public boolean update(DishType dishType) {

        log.info("update() " + dishType.getDishType());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {
            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setString(1, dishType.getDishType());
            sql.setLong(2, dishType.getId());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("update() " + dishType.getDishType());
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }


    private DishType createDishTypeEntity(ResultSet rs) throws SQLException {
        return new DishType(
                rs.getLong("id"),
                rs.getString("dish_type"));
    }
}





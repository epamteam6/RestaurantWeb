package com.dao;

import com.connectionpool.ConnectionPoolManager;
import com.model.DishOrder;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DishOrderDAO implements RegularDAO<DishOrder> {

    private static final Logger log = Logger.getLogger(DishOrderDAO.class);

    private boolean isTestMode = false;
    private DataSource dataSource;

    private ConnectionPoolManager connectionPool = new ConnectionPoolManager();
    private Connection connection;

    private static DishOrderDAO instance;

    private static final String SELECT_BY_ORDER_ID_QUERY = "SELECT * FROM dishes_orders WHERE order_id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dishes_orders WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM dishes_orders";
    private static final String REMOVE_QUERY = "DELETE FROM dishes_orders WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE dishes_orders SET order_id = ?, dish_id = ?, amount = ?, item_sum = ? WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO dishes_orders VALUES (NULL, ?, ?, ?, ?)";

    private DishOrderDAO() {

    }

    public static DishOrderDAO getInstance() {
        if (instance == null)
            instance = new DishOrderDAO();

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
    public Optional<DishOrder> getById(long id) {

        log.info("getById() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        DishOrder dishOrder = null;

        try {

            PreparedStatement sql = connection.prepareStatement(SELECT_BY_ID_QUERY);
            sql.setLong(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next()) {
                dishOrder = createDishOrderEntity(rs);
            }

        } catch (SQLException e) {

            log.error("getById() " + id);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return Optional.ofNullable(dishOrder);
    }

    @Override
    public List<DishOrder> getAll() {

        log.info("getAll()");

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<DishOrder> dishOrders = new ArrayList<>();

        try {

            PreparedStatement sql = connection.prepareStatement(SELECT_ALL_QUERY);

            ResultSet rs = sql.executeQuery();

            while (rs.next())
                dishOrders.add(createDishOrderEntity(rs));

        } catch (SQLException e) {

            log.error("getAll()");
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return dishOrders;
    }


    @Override
    public boolean create(DishOrder dishOrder) {

        log.info("create() " + dishOrder.getId());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, dishOrder.getOrderId());
            sql.setLong(2, dishOrder.getDishId());
            sql.setLong(3, dishOrder.getDishAmount());
            sql.setLong(4, dishOrder.getDishSum());

            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("create() " + dishOrder.getId());
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

            PreparedStatement sql = connection.prepareStatement(REMOVE_QUERY);
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
    public boolean update(DishOrder dishOrder) {

        log.info("update() " + dishOrder.getId());

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setLong(1, dishOrder.getOrderId());
            sql.setLong(2, dishOrder.getDishId());
            sql.setLong(3, dishOrder.getDishAmount());
            sql.setLong(4, dishOrder.getDishSum());
            sql.setLong(5, dishOrder.getId());

            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("update() " + dishOrder.getId());
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }


    public List<DishOrder> getByOrderId(long id) {

        log.info("getByOrderId() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<DishOrder> dishOrders = new ArrayList<>();

        try {

            PreparedStatement sql = connection.prepareStatement(SELECT_BY_ORDER_ID_QUERY);
            sql.setLong(1, id);
            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                dishOrders.add(createDishOrderEntity(rs));
            }

        } catch (SQLException e) {

            log.error("getByOrderId() " + id);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return dishOrders;
    }

    private DishOrder createDishOrderEntity(ResultSet rs) throws SQLException {
        return new DishOrder(
                rs.getInt("id"),
                rs.getInt("order_id"),
                rs.getInt("dish_id"),
                rs.getInt("amount"),
                rs.getInt("item_sum"));
    }
}

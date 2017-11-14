package com.dao;

import com.connectionpool.ConnectionPoolManager;
import com.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderDAO implements RegularDAO<Order> {

    private boolean isTestMode = false;
    private DataSource dataSource;

    private ConnectionPoolManager connectionPool = new ConnectionPoolManager();
    private Connection connection;

    private static OrderDAO instance;

    private static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, date_time=?, total_sum=?, status=? WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO orders(user_id, date_time, total_sum, status)  VALUES (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orders ";
    private static final String SELECT_BY_USERS_STATUS_QUERY = "SELECT * FROM orders WHERE user_id=? AND status=?";

    private OrderDAO() {
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
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
    public Optional<Order> getById(long id) {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        Order order = null;

        try {
            PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                order = createOrderEntity(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return Optional.ofNullable(order);

    }

    @Override
    public List<Order> getAll() {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<Order> res = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createOrderEntity(rs));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return res;
    }


    @Override
    public boolean create(Order order) {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {

            PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setLong(3, order.getTotalSum());
            sql.setString(4, order.getStatus().toString());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    @Override
    public boolean remove(long id) {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {
            PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setLong(1, id);
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;

    }

    @Override
    public boolean update(Order order) {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {
            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setLong(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setLong(3, order.getTotalSum());
            sql.setString(4, order.getStatus().toString());
            sql.setLong(5, order.getId());
            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }


    public List<Order> getByUserAndStatus(long userId, Order.Status status) {

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<Order> res = new ArrayList<>();
        try {
            PreparedStatement sql = connection.prepareStatement(SELECT_BY_USERS_STATUS_QUERY);
            sql.setLong(1, userId);
            sql.setString(2, status.toString());

            ResultSet rs = sql.executeQuery();

            while (rs.next()) {
                res.add(createOrderEntity(rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return res;
    }

    private Order createOrderEntity(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getInt("total_sum"),
                Order.Status.valueOf(rs.getString("Status"))

        );
    }
}

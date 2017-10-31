package com.dao;

import com.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {

    private static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, date_time=?, total_sum=?, status=? WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO orders(user_id, date_time, total_sum, status)  values (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orders";

    private DataSource dataSource;
    private static OrderDAO instance;

    private OrderDAO() { }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    

    public boolean create(Order order) {
        boolean isCreated = false;
        try (Connection connection  = dataSource.getConnection()){

            final PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setInt(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setInt(3, order.getTotalSum());
            sql.setString(4, order.getSt().toString());
            sql.executeUpdate();
            isCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreated;
    }


    public Order getById(int id) {
        Order order = null;

        try (Connection connection  = dataSource.getConnection()){
            final PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setInt(1, id);

            final ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                order = createOrder(rs);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;

    }

    public boolean cancel(int id) {
        boolean isCanceled = false;
        try (Connection connection  = dataSource.getConnection()){
            final PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setInt(1, id);
            sql.executeUpdate();
            isCanceled = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCanceled;

    }

    public boolean update(Order order) {
        boolean isUpdated = false;
        try(Connection connection  = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setInt(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setInt(3, order.getTotalSum());
            sql.setString(4, order.getSt().toString());
            sql.setInt(5, order.getId());
            sql.executeUpdate();
            isUpdated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public List<Order> getAll() {
        List<Order> res = new ArrayList<>();
        Statement statement;
        try (Connection connection  = dataSource.getConnection()){
            statement = connection.createStatement();

            final ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private Order createOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getInt("total_sum"),
                Order.status.valueOf(rs.getString("status"))

        );
    }

}

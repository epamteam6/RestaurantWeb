package com.dao;

import com.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        try (Connection connection  = dataSource.getConnection()){

            final PreparedStatement sql = connection.prepareStatement(INSERT_QUERY);
            sql.setLong(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setLong(3, order.getTotalSum());
            sql.setString(4, order.getStatus().toString());
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Optional<Order> getById(long id) {
        Optional<Order> order = Optional.empty();

        try (Connection connection  = dataSource.getConnection()){
            final PreparedStatement sql = connection.prepareStatement(SELECT_QUERY);
            sql.setLong(1, id);

            final ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                order = Optional.of(createOrderEntity(rs));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;

    }

    public boolean cancel(long id) {
        try (Connection connection  = dataSource.getConnection()){
            final PreparedStatement sql = connection.prepareStatement(DELETE_QUERY);
            sql.setLong(1, id);
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean update(Order order) {
        try(Connection connection  = dataSource.getConnection()) {
            final PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setLong(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setLong(3, order.getTotalSum());
            sql.setString(4, order.getStatus().toString());
            sql.setLong(5, order.getId());
            sql.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getAll() {
        List<Order> res = new ArrayList<>();
        Statement statement;
        try (Connection connection  = dataSource.getConnection()){
            statement = connection.createStatement();

            final ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                res.add(createOrderEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private Order createOrderEntity(ResultSet rs) throws SQLException {
        return new Order(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getLong("total_sum"),
                Order.Status.valueOf(rs.getString("Status"))

        );
    }

}

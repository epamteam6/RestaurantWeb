package com.dao;

import com.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {

    public static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, date_time=?, total_sum=?, status=? where id=?";
    private DataSource dataSource;

    public OrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }


   /* private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }*/

    public boolean create(Order order) {
        boolean isCreated = false;
        try (Connection connection  = dataSource.getConnection()){

            final PreparedStatement sql = connection.prepareStatement("INSERT INTO orders(user_id, date_time, total_sum, status)  values (?,?,?,?)");
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
            final PreparedStatement sql = connection.prepareStatement("Select * from orders where id = ?");
            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getInt("total_sum"),
                        Order.status.valueOf(rs.getString("status"))

                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;

    }

    public boolean cancel(int id) {
        boolean isCanceled = false;
        try (Connection connection  = dataSource.getConnection()){
            final PreparedStatement sql = connection.prepareStatement("DELETE from orders where id=?");
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

            ResultSet rs = statement.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                Order order = new Order(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getInt("total_sum"),
                        Order.status.valueOf(rs.getString("status"))
                );

                res.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

}

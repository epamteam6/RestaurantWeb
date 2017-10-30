package com.dao;

import com.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void createOrder(Order order) {

        try {
            final PreparedStatement sql = connection.prepareStatement("INSERT INTO orders(user_id, date_time, total_sum, status)  values (?,?,?,?)");
            sql.setInt(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setInt(3, order.getTotalSum());
            sql.setString(4, order.getSt().toString());
            sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Order getOrderById(int id) {
        Order order = null;

        try {
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
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;

    }

    public void cancelOrder(int id) {
        try {
            final PreparedStatement sql = connection.prepareStatement("DELETE from orders where id=?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateOrder(Order order) {
        try {
            final PreparedStatement sql = connection.prepareStatement("UPDATE orders SET user_id=?, date_time=?, total_sum=?, status=? where id=?");
            sql.setInt(1, order.getUserId());
            sql.setTimestamp(2, Timestamp.valueOf(order.getDateTime()));
            sql.setInt(3, order.getTotalSum());
            sql.setString(4, order.getSt().toString());
            sql.setInt(5, order.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getAllOrders() {
        List<Order> res = new ArrayList<>();
        Statement statement;
        try {
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

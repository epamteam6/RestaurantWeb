package com.dao;

import com.model.DishOrder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishOrderDAO {

    private static DishOrderDAO instance;
    private DataSource dataSource;

    private static final String SELECT_BY_ORDER_ID_QUERY = "SELECT * FROM dishes_orders WHERE order_id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dishes_orders WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM dishes_orders";
    private static final String REMOVE_QUERY = "DELETE * FROM dishes_orders WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE dishes_orders SET order_id = ? dish_id = ? amount = ? sum = ? WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO dishes_orders VALUES (NULL, ?, ?, ?, ?)";

    private DishOrderDAO() {

    }

    public static DishOrderDAO getInstance() {
        if (instance == null)
            instance = new DishOrderDAO();

        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DishOrder getByOrderId(int id) {
        DishOrder dishOrder = null;

        try (Connection con = dataSource.getConnection()) {

            PreparedStatement sql = con.prepareStatement(SELECT_BY_ORDER_ID_QUERY);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next())
            {
                dishOrder = new DishOrder(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("dish_id"),
                        rs.getInt("amount"),
                        rs.getInt("sum"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishOrder;
    }
}

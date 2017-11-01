package com.dao;

import com.model.DishOrder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishOrderDAO {

    private static DishOrderDAO instance;
    private DataSource dataSource;

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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<DishOrder> getByOrderId(int id) {

        DishOrder dishOrder = null;

        try (Connection con = dataSource.getConnection()) {

            PreparedStatement sql = con.prepareStatement(SELECT_BY_ORDER_ID_QUERY);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next())
            {
                dishOrder = createDishOrderEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(dishOrder);
    }

    public Optional<DishOrder> getById(int id) {

        DishOrder dishOrder = null;

        try (Connection con = dataSource.getConnection()) {

            PreparedStatement sql = con.prepareStatement(SELECT_BY_ID_QUERY);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();

            if (rs.next())
            {
                dishOrder = createDishOrderEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(dishOrder);
    }

    public List<DishOrder> getAll() {

        List<DishOrder> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(SELECT_ALL_QUERY);

            ResultSet rs = sql.executeQuery();

            while (rs.next())
                users.add(createDishOrderEntity(rs));

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    public boolean remove(int id) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(REMOVE_QUERY);
            sql.setInt(1, id);

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean update(DishOrder dishOrder) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setInt(1, dishOrder.getOrderId());
            sql.setInt(2, dishOrder.getDishId());
            sql.setInt(3, dishOrder.getDishAmount());
            sql.setInt(4, dishOrder.getDishSum());
            sql.setInt(5, dishOrder.getId());

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
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

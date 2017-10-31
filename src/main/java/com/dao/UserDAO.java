package com.dao;

import com.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private DataSource dataSource;

    private static final String IS_EXIST_QUERY = "SELECT * FROM users WHERE user_name = ?";
    private static final String VALIDATION_QUERY = "SELECT * FROM users WHERE user_name = ? AND password_hash = ?";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM users WHERE user_name = ?";
    private static final String ADD_QUERY = "INSERT INTO users VALUES (NULL, ?, ?, ?)";
    private static final String REMOVE_QUERY = "DELETE FROM users WHERE user_name = ?";
    private static final String UPDQTE_QUERY = "UPDATE users SET is_admin = ?, password_hash = ? WHERE user_name = ?";

    private UserDAO() {
    }

    public static UserDAO getInstance() {

        if (instance == null) {

            instance = new UserDAO();
        }

        return instance;
    }

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public boolean isExist(String userName) {

        boolean isPresent = false;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(IS_EXIST_QUERY);
            sql.setString(1, userName);
            ResultSet rs = sql.executeQuery();

            isPresent = rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isPresent;
    }

    public boolean validate(String userName, String password) {

        boolean isPresent = false;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(VALIDATION_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            ResultSet rs = sql.executeQuery();

            isPresent = rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isPresent;
    }

    public User getByName(String userName) {

        User user = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(GET_BY_NAME_QUERY);
            sql.setString(1, userName);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                user = createUser(rs);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    public User add(String userName, String password, boolean isAdmin) {

        //in service class check if user exists

        User user = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(ADD_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            sql.setBoolean(3, isAdmin);

            sql.executeUpdate();

            user = getByName(userName);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    public boolean remove(String userName) {

        //in service class check if user exists or admin

        boolean isRemoved = false;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(REMOVE_QUERY);
            sql.setString(1, userName);

            sql.executeUpdate();

            isRemoved = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isRemoved;
    }

    public boolean update(String userName, String password, boolean isAdmin) {

        //in service class check if user exists

        boolean isUpdated = false;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(UPDQTE_QUERY);
            sql.setBoolean(1, isAdmin);
            sql.setString(2, password);
            sql.setString(3, userName);

            sql.executeUpdate();

            isUpdated = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            while (rs.next())
                users.add(createUser(rs));

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    private User createUser(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt("id"),
                rs.getString("user_name"),
                rs.getBoolean("is_admin"));
    }
}

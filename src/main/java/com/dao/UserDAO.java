package com.dao;

import com.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAO {

    private DataSource dataSource;
    private static UserDAO instance;

    private static final String VALIDATION_QUERY = "SELECT * FROM users WHERE user_name = ? AND password_hash = ?";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM users WHERE user_name = ?";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO users VALUES (NULL, ?, ?, ?)";
    private static final String REMOVE_QUERY = "DELETE FROM users WHERE user_name = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET is_admin = ?, password_hash = ? WHERE user_name = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM users";

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


    public Optional<User> getById(long id) {

        User user = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(GET_BY_ID_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                user = createUserEntity(rs);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(GET_ALL_QUERY);

            ResultSet rs = sql.executeQuery();

            while (rs.next())
                users.add(createUserEntity(rs));

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }


    public boolean add(String userName, String password, boolean isAdmin) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(ADD_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            sql.setBoolean(3, isAdmin);

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean remove(String userName) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(REMOVE_QUERY);
            sql.setString(1, userName);

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean update(String userName, String password, boolean isAdmin) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setBoolean(1, isAdmin);
            sql.setString(2, password);
            sql.setString(3, userName);

            sql.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean validate(String userName, String password) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(VALIDATION_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            ResultSet rs = sql.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public Optional<User> getByName(String userName) {

        User user = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement sql = connection.prepareStatement(GET_BY_NAME_QUERY);
            sql.setString(1, userName);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                user = createUserEntity(rs);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }


    private User createUserEntity(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt("id"),
                rs.getString("user_name"),
                rs.getBoolean("is_admin"));
    }
}

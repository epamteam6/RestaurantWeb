package com.dao;

import com.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private DataSource dataSource;

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

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    String.format("SELECT * FROM users WHERE user_name = '%s'", userName));

            isPresent = rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isPresent;
    }

    public boolean validate(String userName, String password) {

        boolean isPresent = false;

        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    String.format("SELECT * FROM users WHERE user_name = '%s' " +
                            "AND password_hash = '%s'", userName, password));

            isPresent = rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return isPresent;
    }

    public User getByName(String userName) {

        User user = null;

        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM users WHERE user_name = '%s'", userName);
            ResultSet rs = statement.executeQuery(query);

            if (rs.next())
                user = createUser(rs);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return user;
    }

    public boolean add(String userName, String password, boolean isAdmin) {

        //in service class check if user exists

        boolean isCreated = false;
        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            String query =
                    String.format("INSERT INTO users " +
                            "VALUES (NULL, '%s', '%s', %b)", userName, password, isAdmin);
            statement.executeUpdate(query);

            isCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isCreated;
    }

    public boolean remove(String userName) {

        //in service class check if user exists or admin

        boolean isRemoved = false;

        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();

            String query =
                    String.format("DELETE FROM users WHERE user_name = '%s'", userName);
            statement.executeUpdate(query);

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

            Statement statement = connection.createStatement();

            String query = String.format("UPDATE users SET is_admin = %b, " +
                            "password_hash = '%s' WHERE user_name = '%s'",
                    isAdmin, password, userName);

            statement.executeUpdate(query);

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

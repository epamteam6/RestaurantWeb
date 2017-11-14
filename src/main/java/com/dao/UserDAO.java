package com.dao;

import com.connectionpool.ConnectionPoolManager;
import com.model.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAO implements DAO<User> {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    private boolean isTestMode = false;
    private DataSource dataSource;

    private ConnectionPoolManager connectionPool = new ConnectionPoolManager();
    private Connection connection;

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
    public Optional<User> getById(long id) {

        log.info("getById() " + id);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        User user = null;

        try {

            PreparedStatement sql = connection.prepareStatement(GET_BY_ID_QUERY);
            sql.setLong(1, id);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                user = createUserEntity(rs);

        } catch (SQLException e) {

            log.error("getById() " + id);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {

        log.info("getAll()");

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        List<User> users = new ArrayList<>();

        try {

            PreparedStatement sql = connection.prepareStatement(GET_ALL_QUERY);

            ResultSet rs = sql.executeQuery();

            while (rs.next())
                users.add(createUserEntity(rs));

        } catch (SQLException e) {

            log.error("getAll()");
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return users;
    }


    public boolean add(String userName, String password, boolean isAdmin) {

        log.info("add() " + userName);

        boolean result = false;

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        try {

            PreparedStatement sql = connection.prepareStatement(ADD_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            sql.setBoolean(3, isAdmin);

            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("add() " + userName);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    public boolean remove(String userName) {

        log.info("remove() " + userName);

        boolean result = false;

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        try {

            PreparedStatement sql = connection.prepareStatement(REMOVE_QUERY);
            sql.setString(1, userName);

            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("remove() " + userName);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    public boolean update(String userName, String password, boolean isAdmin) {

        log.info("update() " + userName);

        boolean result = false;

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        try {

            PreparedStatement sql = connection.prepareStatement(UPDATE_QUERY);
            sql.setBoolean(1, isAdmin);
            sql.setString(2, password);
            sql.setString(3, userName);

            sql.executeUpdate();

            result = true;

        } catch (SQLException e) {

            log.error("update() " + userName);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    public boolean validate(String userName, String password) {

        log.info("validate() " + userName);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        boolean result = false;

        try {
            PreparedStatement sql = connection.prepareStatement(VALIDATION_QUERY);
            sql.setString(1, userName);
            sql.setString(2, password);
            ResultSet rs = sql.executeQuery();

            result = rs.next();

        } catch (SQLException e) {

            log.error("validate() " + userName);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return result;
    }

    public Optional<User> getByName(String userName) {

        log.info("getByName() " + userName);

        if (!isTestMode)
            connection = connectionPool.getConnectionFromPool();

        User user = null;

        try {

            PreparedStatement sql = connection.prepareStatement(GET_BY_NAME_QUERY);
            sql.setString(1, userName);

            ResultSet rs = sql.executeQuery();

            if (rs.next())
                user = createUserEntity(rs);

        } catch (SQLException e) {

            log.error("getByName() " + userName);
            e.printStackTrace();
        }

        if (!isTestMode)
            connectionPool.returnConnectionToPool(connection);

        return Optional.ofNullable(user);
    }


    private User createUserEntity(ResultSet rs) throws SQLException {

        return new User(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getBoolean("is_admin"));
    }
}

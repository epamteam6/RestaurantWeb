package com.connectionpool;

import java.util.*;
import java.sql.*;

public class ConnectionPoolManager {

    String databaseUrl = "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true";
    String userName = "root";
    String password = "root";

    Vector connectionPool = new Vector();

    public ConnectionPoolManager() {
        initialize();
    }

    public ConnectionPoolManager(
            String databaseUrl,
            String userName,
            String password
    ) {
        this.databaseUrl = databaseUrl;
        this.userName = userName;
        this.password = password;
        initialize();
    }

    private void initialize() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
            connectionPool.addElement(createNewConnectionForPool());
        }
        System.out.println("Connection Pool is full.");
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        final int MAX_POOL_SIZE = 5;

        //Check if the pool size
        if (connectionPool.size() < 5) {
            return false;
        }

        return true;
    }

    //Creating a connection
    private Connection createNewConnectionForPool() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            System.out.println("Connection: " + connection);
        } catch (SQLException sqle) {
            System.err.println("SQLException: " + sqle);
            return null;
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException: " + cnfe);
            return null;
        }

        return connection;
    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;

        if (connectionPool.size() > 0) {
            connection = (Connection) connectionPool.firstElement();
            connectionPool.removeElementAt(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection) {
        connectionPool.addElement(connection);
    }

    public static void main(String args[]) {
        ConnectionPoolManager ConnectionPoolManager = new ConnectionPoolManager();
    }

}

package com.connectionpool;

import java.util.*;
import java.sql.*;

public class ConnectionPoolManager {

    private String databaseUrl = "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true";
    private String userName = "root";
    private String password = "root";

    private Queue<Connection> connectionPool = new LinkedList<>();
    private final int MAX_POOL_SIZE = 5;

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
            connectionPool.add(createNewConnectionForPool());
        }
        System.out.println("Connection Pool is full.");
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {

        //Check if the pool size
        return (connectionPool.size() < MAX_POOL_SIZE);
    }

    //Creating a connection
    private Connection createNewConnectionForPool() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            System.out.println("Connection: " + connection);

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            return null;
        }

        return connection;
    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;

        if (connectionPool.size() > 0) {
            connection = connectionPool.poll();
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection) {
        connectionPool.add(connection);
    }

//    public static void main(String args[]) {
//        ConnectionPoolManager ConnectionPoolManager = new ConnectionPoolManager();
//    }

}

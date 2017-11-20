package com.connectionpool;

import org.apache.log4j.Logger;

import java.util.*;
import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPoolManager {

    private static final Logger log = Logger.getLogger(ConnectionPoolManager.class);

    private String databaseUrl = "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true";
    private String userName = "root";
    private String password = "root";

    private final BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<Connection>(MAX_POOL_SIZE);
    private static final int MAX_POOL_SIZE = 5;

    public ConnectionPoolManager() {
        initialize();
    }

//    public ConnectionPoolManager(
//            String databaseUrl,
//            String userName,
//            String password
//    ) {
//        this.databaseUrl = databaseUrl;
//        this.userName = userName;
//        this.password = password;
//        initialize();
//    }

    private void initialize() {

        log.info("Initialising connections in ConnectionPoolManager");

        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        while (checkIfConnectionPoolIsFull()) {
//            System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
            connectionPool.add(createNewConnectionForPool());
        }
        log.debug("Connection Pool is full");
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {

        //Check if the pool size
        return (connectionPool.size() < MAX_POOL_SIZE);
    }

    //Creating a connection
    private synchronized Connection createNewConnectionForPool() {
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            System.out.println("Connection: " + connection);

        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

            return null;
        }

        return connection;
    }

    public Connection getConnectionFromPool() {

        synchronized (connectionPool) {

            log.debug("Getting connection from pool");

            while (connectionPool.isEmpty()) {

                try {
                    connectionPool.wait(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return connectionPool.poll();
        }
    }

    public void returnConnectionToPool(Connection connection) {

        synchronized (connectionPool) {

            log.debug("Returning connection to pool");

            connectionPool.offer(connection);
            connectionPool.notify();
        }
    }
}

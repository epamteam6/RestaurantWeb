package com.dao;

import com.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAOTest
{
    private UserDAO userDAO;
    private Connection con;

    @Before
    public void init() throws Exception
    {
        con = getConnection();
        userDAO = new UserDAO(con);
    }

    @Test
    public void isUserExist() throws Exception
    {
        System.out.println(userDAO.isUserExist("Admin")); //true
        System.out.println(userDAO.isUserExist("admin")); //false
    }

    @Test
    public void addUser()
    {
        System.out.println(userDAO.addUser("Temp", "temp")); //true
        System.out.println(userDAO.addUser("Temp", "temp")); //false
    }

    @Test
    public void getAllUsers() throws Exception
    {
        Statement statement = con.createStatement();

        List<User> users = userDAO.getAllUsers();

        users.forEach(System.out::println);
    }

    private Connection getConnection() throws Exception
    {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/restaurant-web-db?serverTimezone=UTC",
                "root",
                "password");
    }
}

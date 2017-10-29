package com.dao;

import com.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public void validateUser() throws Exception
    {
        System.out.println(userDAO.validateUser("Admin", "admin")); //true
        System.out.println(userDAO.validateUser("Admin", "aDmin")); //false
    }

    @Test
    public void getUser() throws Exception
    {
        System.out.println(userDAO.getUserByName("Admin"));
        System.out.println(userDAO.getUserByName("Temp"));
    }

    @Test
    public void addUser()
    {
        System.out.println(userDAO.addUser("Temp", "temp", true)); //true
        System.out.println(userDAO.addUser("Temp", "temp", true)); //false
    }

    @Test
    public void removeUser()
    {
        System.out.println(userDAO.removeUser("Temp")); //true
        System.out.println(userDAO.removeUser("Temp")); //false
    }

    @Test
    public void updateUser()
    {
        userDAO.updateUser("Temp", "temp1", false);
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

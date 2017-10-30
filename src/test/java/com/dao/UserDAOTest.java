package com.dao;

import com.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
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
//        con = getConnection();
        userDAO = new UserDAO(getDataSource());
    }

    @Test
    public void isUserExist() throws Exception
    {
        System.out.println(userDAO.isExist("Admin")); //true
        System.out.println(userDAO.isExist("admin")); //false
    }

    @Test
    public void validateUser() throws Exception
    {
        System.out.println(userDAO.validate("Admin", "admin")); //true
        System.out.println(userDAO.validate("Admin", "aDmin")); //false
    }

    @Test
    public void getUser() throws Exception
    {
        System.out.println(userDAO.getByName("Admin"));
        System.out.println(userDAO.getByName("Temp"));
    }

    @Test
    public void addUser()
    {
        System.out.println(userDAO.add("Temp", "temp", true)); //true
        System.out.println(userDAO.add("Temp", "temp", true)); //false
    }

    @Test
    public void removeUser()
    {
        System.out.println(userDAO.remove("Temp")); //true
        System.out.println(userDAO.remove("Temp")); //false
    }

    @Test
    public void updateUser()
    {
        userDAO.update("Temp", "temp1", false);
    }

    @Test
    public void getAllUsers() throws Exception
    {
        List<User> users = userDAO.getAll();

        users.forEach(System.out::println);
    }

    //real localhost connection
    private Connection getConnection() throws Exception
    {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/restaurant-web-db?serverTimezone=UTC",
                "root",
                "password");
    }

    public DataSource getDataSource()
    {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init_users.sql")
                .addScript("data_users.sql")
                .build();

        return dataSource;
    }
}

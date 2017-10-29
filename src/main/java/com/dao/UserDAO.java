package com.dao;

import com.entity.User;

import java.sql.*;
import java.util.List;

public class UserDAO
{
    private Connection connection;

    public UserDAO(Connection connection)
    {
        this.connection = connection;
    }

    public boolean isUserExist(String userName)
    {
        boolean isPresent = false;
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    String.format("SELECT * FROM users WHERE BINARY user_name = '%s'", userName));

            isPresent = rs.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isPresent;
    }

    public boolean addUser(String userName, String password)
    {
        if (isUserExist(userName))
            return false;

        boolean isCreated = false;
        try
        {
            Statement statement = connection.createStatement();

            String query =
                    String.format("INSERT INTO users VALUES (NULL, '%s', '%s', 0)", userName, password);
            statement.executeUpdate(query);

            isCreated = true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isCreated;
    }

    public boolean removeUser(String userName)
    {
        if (!isUserExist(userName))
            return false;

        boolean isRemoved = false;
        try
        {
            Statement statement = connection.createStatement();

            String query =
                    String.format("DELETE FROM users WHERE user_name = '%s'", userName);
            statement.executeUpdate(query);

            isRemoved = true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isRemoved;
    }

    public List<User> getAllUsers()
    {


        return null;
    }
}

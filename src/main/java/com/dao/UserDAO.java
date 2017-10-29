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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return isPresent;
    }

    public List<User> getAllUsers()
    {


        return null;
    }
}

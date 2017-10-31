package com.dao;

import com.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOTest {

    private UserDAO userDAO = UserDAO.getInstance();
//    private Connection con;

    @Before
    public void init() throws Exception {

//        con = getConnection();
        userDAO.setDataSource(getDataSource());
    }

    @Test
    public void isUserExist() throws Exception {

        boolean act1 = userDAO.isExist("Admin"); //true
        boolean act2 = userDAO.isExist("admin"); //false

        assertThat(act1, is(true));
        assertThat(act2, is(false));
    }

    @Test
    public void validateUser() throws Exception {

        boolean act1 = userDAO.validate("Admin", "admin");
        boolean act2 = userDAO.validate("Admin", "aDmin");

        assertThat(act1, is(true));
        assertThat(act2, is(false));
    }

    @Test
    public void getUser() throws Exception {

        User act1 = userDAO.getByName("Admin");
        User exp1 = new User(3, "Admin", true);

        assertThat(act1, is(exp1));
    }

    @Test
    public void addUser() {

        userDAO.add("Temp", "temp", true);


    }

    @Test
    public void removeUser() {

        System.out.println(userDAO.remove("Temp")); //true
        System.out.println(userDAO.remove("Temp")); //false
    }

    @Test
    public void updateUser() {

        userDAO.update("Temp", "temp1", false);
    }

    @Test
    public void getAllUsers() throws Exception {

        List<User> users = userDAO.getAll();

        users.forEach(System.out::println);
    }

    //real localhost connection
//    private Connection getConnection() throws Exception {
//
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/restaurant-web-db?serverTimezone=UTC",
//                "root",
//                "password");
//    }

    private DataSource getDataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init_users.sql")
                .addScript("data_users.sql")
                .build();
    }
}

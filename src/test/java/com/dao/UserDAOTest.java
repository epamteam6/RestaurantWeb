package com.dao;

import com.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.sql.DataSource;
import java.util.ArrayList;
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

        User act = userDAO.add("Temp", "temp", true);
        User exp = new User(7, "Temp", true);

        assertThat(act, is(exp));
    }

    @Test
    public void removeUser() {

        assertThat(userDAO.isExist("Ivanov"), is(true));
        userDAO.remove("Ivanov");
        assertThat(userDAO.isExist("Ivanov"), is(false));
    }

    @Test
    public void updateUser() {

        boolean act1 = userDAO.getByName("Admin").isAdmin();
        assertThat(act1, is(true));

        userDAO.update("Admin", "aaaaa", false);
        boolean act2 = userDAO.getByName("Admin").isAdmin();
        assertThat(act2, is(false));
    }

    @Test
    public void getAllUsers() throws Exception {

        List<User> act = userDAO.getAll();

        List<User> exp = new ArrayList<>();
        exp.add(new User(1, "Petrov", false));
        exp.add(new User(2, "Ivanov", false));
        exp.add(new User(3, "Admin", true));
        exp.add(new User(4, "Rustam", false));
        exp.add(new User(5, "Sergei", false));
        exp.add(new User(6, "Yulia", false));

        assertThat(act, is(exp));
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

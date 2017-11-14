package com.integrational_dao;

import com.dao.UserDAO;
import com.model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IntegroUserDAO {

    private UserDAO userDAO = UserDAO.getInstance();

    @Test
    public void getById() {
        User act1 = userDAO.getById(3).get();
        User exp1 = new User(3, "Admin", true);

        assertThat(act1, is(exp1));
    }

    @Test
    public void validate() {
        boolean act = userDAO.validate("Admin", "admin");
        assertTrue(act);
    }

    @Test
    public void getUserByName() {

        User act1 = userDAO.getByName("Admin").get();
        User exp1 = new User(3, "Admin", true);

        assertThat(act1, is(exp1));
    }

    // getAll

    // add

    // remove

    // update
}

package com.service;

import com.dao.UserDAO;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorisationServiceTest {

    private final AuthorisationService service = AuthorisationService.getInstance();

    @Test
    public void singInTest()
    {
        UserDAO userDAO = mock(UserDAO.class);

        when(userDAO.validate("Admin", "admin")).thenReturn(true);

        service.setUserDAO(userDAO);

        boolean check = service.singIn("Admin", "admin");
        verify(userDAO, atLeastOnce()).validate("Admin", "admin");

        assertTrue(check);
    }
}

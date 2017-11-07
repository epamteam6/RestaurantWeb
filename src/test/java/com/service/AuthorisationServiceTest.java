package com.service;

import com.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorisationServiceTest {

    private final AuthorisationService service = AuthorisationService.getInstance();
    @Mock UserDAO userDAO;

    @Test
    public void singInTest()
    {

        when(userDAO.validate("Admin", "admin")).thenReturn(true);

        service.setUserDAO(userDAO);

        boolean check = service.singIn("Admin", "admin");
        verify(userDAO, atLeastOnce()).validate("Admin", "admin");

        assertTrue(check);
    }
}

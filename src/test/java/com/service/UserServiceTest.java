package com.service;

import com.dao.UserDAO;
import com.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static UserService service;
    private static UserDAO userDAOMock;

    @Before
    public void init() {
        service = UserService.getInstance();
        userDAOMock = mock(UserDAO.class);
        service.setUserDAO(userDAOMock);
    }

    @Test
    public void registerNotUsedUsernameTest() {
        String usernameNotExist = "Temp";
        String whatEverPassword = "whatever...";
        boolean defaultStatus = false;

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));
        when(userDAOMock.add(usernameNotExist, whatEverPassword, defaultStatus)).thenReturn(true);

        boolean regWhenNewName = service.register(usernameNotExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);
        verify(userDAOMock, atLeastOnce()).add(usernameNotExist, whatEverPassword, defaultStatus);
        assertTrue(regWhenNewName);
    }

    @Test
    public void registerUsedUsernameTest() {
        String usernameExist = "Admin";
        String whatEverPassword = "whatever...";
        boolean defaultStatus = false;

        Optional<User> opt = Optional.of(new User(1, usernameExist, defaultStatus));

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);

        boolean regWhenNotNewName = service.register(usernameExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        assertFalse(regWhenNotNewName);
    }

    @Test
    public void removeWhenExistsTest() {
        String usernameExist = "Admin";
        boolean defaultStatus = false;

        Optional<User> opt = Optional.of(new User(1, usernameExist, defaultStatus));

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);
        when(userDAOMock.remove(usernameExist)).thenReturn(true);

        boolean removeWhenExists = service.remove(usernameExist);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        assertTrue(removeWhenExists);
    }

    @Test
    public void removeWhenNotExistsTest() {
        String usernameNotExist = "Temp";
        boolean defaultStatus = false;

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));

        boolean removeWhenNotExists = service.remove(usernameNotExist);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);
        assertFalse(removeWhenNotExists);
    }
}

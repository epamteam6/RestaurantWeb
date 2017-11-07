package com.service;

import com.dao.UserDAO;
import com.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock private UserDAO userDAOMock;
    private UserService service;

    private String usernameExist = "Admin";
    private String usernameNotExist = "Temp";
    private String whatEverPassword = "whatever...";
    private boolean defaultStatus = false;

    private Optional<User> opt = Optional.of(new User(1, usernameExist, defaultStatus));

    @Before
    public void init() {
        service = UserService.getInstance();
        service.setUserDAO(userDAOMock);
    }


    @Test
    public void registerUsedUsername() {

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);

        boolean regWhenNotNewName = service.register(usernameExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        assertFalse(regWhenNotNewName);
    }

    @Test
    public void registerNotUsedUsername() {

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));
        when(userDAOMock.add(usernameNotExist, whatEverPassword, defaultStatus)).thenReturn(true);

        boolean regWhenNewName = service.register(usernameNotExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);
        verify(userDAOMock, atLeastOnce()).add(usernameNotExist, whatEverPassword, defaultStatus);
        assertTrue(regWhenNewName);
    }


    @Test
    public void removeWhenExists() {

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);
        when(userDAOMock.remove(usernameExist)).thenReturn(true);

        boolean removeWhenExists = service.remove(usernameExist);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        assertTrue(removeWhenExists);
    }

    @Test
    public void removeWhenNotExists() {

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));

        boolean removeWhenNotExists = service.remove(usernameNotExist);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);
        assertFalse(removeWhenNotExists);
    }


    @Test
    public void passwordChangeWhenExists() {

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);
        when(userDAOMock.update(usernameExist, whatEverPassword, opt.get().isAdmin())).thenReturn(true);

        boolean setPasswordWhenExist = service.changePassword(usernameExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        verify(userDAOMock, atLeastOnce()).update(usernameExist, whatEverPassword, opt.get().isAdmin());

        assertTrue(setPasswordWhenExist);
    }

    @Test
    public void passwordChangeWhenNotExists() {

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));

        boolean setPasswordWhenNotExist = service.changePassword(usernameNotExist, whatEverPassword);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);

        assertFalse(setPasswordWhenNotExist);
    }


    @Test
    public void statusChangeWhenExists() {

        when(userDAOMock.getByName(usernameExist)).thenReturn(opt);
        when(userDAOMock.update(usernameExist, null, defaultStatus)).thenReturn(true);

        boolean setStatusWhenExist = service.changeAdminStatus(usernameExist, defaultStatus);

        verify(userDAOMock, atLeastOnce()).getByName(usernameExist);
        verify(userDAOMock, atLeastOnce()).update(usernameExist, null, defaultStatus);

        assertTrue(setStatusWhenExist);
    }

    @Test
    public void statusChangeWhenNotExists() {

        when(userDAOMock.getByName(usernameNotExist)).thenReturn(Optional.ofNullable(null));

        boolean setStatusWhenNotExist = service.changeAdminStatus(usernameNotExist, defaultStatus);

        verify(userDAOMock, atLeastOnce()).getByName(usernameNotExist);

        assertFalse(setStatusWhenNotExist);
    }
}

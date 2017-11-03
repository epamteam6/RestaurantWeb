package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;
import com.model.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private static OrderService service;

    private OrderDAO orderDAOMock;
    private UserDAO userDAOMock;
    private DishTypeDAO dishTypeDAOMock;
    private DishDAO dishDAOMock;
    private DishOrderDAO dishOrderDAOMock;

    private Optional<Order> optOrder;

    private List<Dish> dishes = Arrays.asList(
            new Dish(1, "MOJITO", 1, 100),
            new Dish(2, "BURITO", 2, 100)
    );
    private List<DishType> dishTypes = Arrays.asList(
            new DishType(1, "DRINKS"),
            new DishType(2, "SHAVERMAS")
    );

    @Before
    public void init() {
        service = OrderService.getInstance();

        orderDAOMock = mock(OrderDAO.class);
        userDAOMock = mock(UserDAO.class);
        dishTypeDAOMock = mock(DishTypeDAO.class);
        dishDAOMock = mock(DishDAO.class);
        dishOrderDAOMock = mock(DishOrderDAO.class);

        service.setOrderDAO(orderDAOMock);
        service.setUserDAO(userDAOMock);
        service.setDishTypeDAO(dishTypeDAOMock);
        service.setDishDAO(dishDAOMock);
        service.setDishOrderDAO(dishOrderDAOMock);
    }

    @Test
    public void getMenu() throws Exception {

        when(dishDAOMock.getAll()).thenReturn(dishes);
        when(dishTypeDAOMock.getAll()).thenReturn(dishTypes);
        when(dishTypeDAOMock.getById(1)).thenReturn(Optional.of(dishTypes.get(0)));
        when(dishTypeDAOMock.getById(2)).thenReturn(Optional.of(dishTypes.get(1)));

        Map<String, Map<String, Long>> menu = service.getMenu();

        String act = menu.toString();
        String exp = "{DRINKS={MOJITO=100}, SHAVERMAS={BURITO=100}}";

        verify(dishDAOMock, atLeastOnce()).getAll();
        verify(dishTypeDAOMock, atLeastOnce()).getAll();
        verify(dishTypeDAOMock, atLeastOnce()).getById(1);
        verify(dishTypeDAOMock, atLeastOnce()).getById(2);

        assertEquals(act, exp);
    }

}
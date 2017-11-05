package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;
import com.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private static OrderService service;

    @Mock private OrderDAO orderDAOMock;
    @Mock private UserDAO userDAOMock;
    @Mock private DishTypeDAO dishTypeDAOMock;
    @Mock private DishDAO dishDAOMock;
    @Mock private DishOrderDAO dishOrderDAOMock;

    private Optional<Order> optOrder;

    private List<Dish> dishes = Arrays.asList(
            new Dish(1, "MOJITO", 1, 100),
            new Dish(2, "BURRITO", 2, 100)
    );
    private List<DishType> dishTypes = Arrays.asList(
            new DishType(1, "DRINKS"),
            new DishType(2, "SHAVERMAS")
    );

    @Before
    public void init() {
        service = OrderService.getInstance();

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

        String act = menuToString(menu);
        String exp = "{DRINKS={MOJITO=100}, SHAVERMAS={BURRITO=100}}";

        verify(dishDAOMock, atLeastOnce()).getAll();
        verify(dishTypeDAOMock, atLeastOnce()).getAll();
        verify(dishTypeDAOMock, atLeastOnce()).getById(1);
        verify(dishTypeDAOMock, atLeastOnce()).getById(2);

        assertEquals(act, exp);
    }

    private String menuToString(Map<String, Map<String, Long>> menu) {
        return menu.toString();
    }


}
package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;
import com.model.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private static OrderService service;

    private OrderDAO orderDAOMock;
    private UserDAO userDAOMock;
    private DishTypeDAO dishTypeDAOMock;
    private DishDAO dishDAOMock;
    private DishOrderDAO dishOrderDAOMock;

    private static Optional<Order> order;

    @Before
    public void init()
    {
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

        when(dishDAOMock.getAll()).thenReturn(anyListOf(Dish.class));
        when(dishTypeDAOMock.getAll()).thenReturn(anyListOf(DishType.class));
        when(dishTypeDAOMock.getById(anyLong())).thenReturn(Optional.of(any(DishType.class)));
        service.getMenu();
        //System.out.println(OrderService.getInstance().getMenu());
    }

}
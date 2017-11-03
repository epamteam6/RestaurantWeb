package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;
import com.model.Order;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private static final OrderService orderService = OrderService.getInstance();
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private DishTypeDAO dishTypeDAO;
    private DishDAO dishDAO;
    private DishOrderDAO dishOrderDAO;

    private static Optional<Order> order;


    @Test
    public void getMenu() throws Exception {
        dishDAO = mock(DishDAO.getInstance().getClass());
        dishTypeDAO = mock(DishTypeDAO.getInstance().getClass());

        when(dishDAO.getAll()).thenReturn(anyListOf(Dish.class));
        when(dishTypeDAO.getAll()).thenReturn(anyListOf(DishType.class));
        when(dishTypeDAO.getById(anyLong())).thenReturn(Optional.of(any(DishType.class)));
        orderService.getMenu();
        //System.out.println(OrderService.getInstance().getMenu());
    }

}
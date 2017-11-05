package com.service;

import com.dao.*;
import com.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private static OrderService service;

    @Mock
    private OrderDAO orderDAOMock;
    @Mock
    private UserDAO userDAOMock;
    @Mock
    private DishTypeDAO dishTypeDAOMock;
    @Mock
    private DishDAO dishDAOMock;
    @Mock
    private DishOrderDAO dishOrderDAOMock;

    private Optional<Order> optOrder;

    private List<Dish> dishes = Arrays.asList(
            new Dish(1, "MOJITO", 1, 100),
            new Dish(2, "BURRITO", 2, 100)
    );
    private List<DishType> dishTypes = Arrays.asList(
            new DishType(1, "DRINKS"),
            new DishType(2, "SHAVERMAS")
    );
    private List<DishOrder> dishOrders = Arrays.asList(
            new DishOrder(1, 1, 1, 1, 100),
            new DishOrder(2, 1, 2, 2, 200)
    );
    private List<Order> orders = Arrays.asList(
            new Order(1, 1,
                    LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                    300, Order.Status.CREATED)
    );
    private List<User> users = Arrays.asList(
            new User(1, "Ivan", false)
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
    public void showOrdersWithDetails() throws Exception {
        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.of(users.get(0)));
        when(orderDAOMock.getAll()).thenReturn(orders);
        when(dishOrderDAOMock.getAll()).thenReturn(dishOrders);
        when(dishDAOMock.getById(1)).thenReturn(Optional.of(dishes.get(0)));
        when(dishDAOMock.getById(2)).thenReturn(Optional.of(dishes.get(1)));

        Map<Long, Map<String, Long>> ordersDetails = service.showOrdersWithDetails("Ivan", Order.Status.CREATED);

        String act = ordersDetails.toString();

        String exp = "{1={BURRITO=2, MOJITO=1}}";

        verify(userDAOMock, times(1)).getByName(anyString());
        verify(orderDAOMock, times(1)).getAll();
        verify(dishOrderDAOMock, times(1)).getAll();
        verify(dishDAOMock, times(2)).getById(anyLong());

        assertEquals(act, exp);
    }


    @Test
    public void cancelOrder() throws Exception {
        when(orderDAOMock.remove(anyLong())).thenReturn(true);

        boolean check = service.cancelOrder(anyLong());
        verify(orderDAOMock, times(1)).remove(anyLong());

        assertTrue(check);
    }


}
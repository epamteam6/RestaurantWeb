package com.service;

import com.dao.*;
import com.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

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
    private DishDAO dishDAOMock;
    @Mock
    private DishOrderDAO dishOrderDAOMock;

    private List<Dish> dishes = Arrays.asList(
            new Dish(1, "MOJITO", 1, 100),
            new Dish(2, "BURRITO", 2, 100)
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

    private Map<String, Long> userChoice = new HashMap<>();
    long amount_1 = 10;
    long amount_2 = 5;


    @Before
    public void init() {
        service = OrderService.getInstance();

        service.setOrderDAO(orderDAOMock);
        service.setUserDAO(userDAOMock);
        service.setDishDAO(dishDAOMock);
        service.setDishOrderDAO(dishOrderDAOMock);

        userChoice.put("MOJITO", amount_1);
        userChoice.put("BURRITO", amount_2);
    }

    @Test
    public void showOrdersWithDetailsValid() {
        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.of(users.get(0)));
        when(orderDAOMock.getByUserAndStatus(1, Order.Status.CREATED)).thenReturn(orders);
        when(dishOrderDAOMock.getByOrderId(1)).thenReturn(dishOrders);
        when(dishDAOMock.getById(1)).thenReturn(Optional.of(dishes.get(0)));
        when(dishDAOMock.getById(2)).thenReturn(Optional.of(dishes.get(1)));

        Map<Long, Map<String, Long>> ordersDetails = service.orderDetails("Ivan", Order.Status.CREATED);

        String act = ordersDetails.toString();

        String exp = "{1={BURRITO=2, MOJITO=1}}";

        verify(userDAOMock, times(1)).getByName(anyString());
        verify(orderDAOMock, times(1)).getByUserAndStatus(1, Order.Status.CREATED);
        verify(dishOrderDAOMock, times(1)).getByOrderId(1);
        verify(dishDAOMock, times(2)).getById(anyLong());

        verifyNoMoreInteractions(userDAOMock, orderDAOMock, dishOrderDAOMock, dishDAOMock);

        assertEquals(act, exp);
    }

    @Test(expected = NoSuchElementException.class)
    public void showOrdersWithDetailsNoUser() {

        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.ofNullable(null));
        service.orderDetails("Ivan", Order.Status.CREATED);
    }

    @Test(expected = NoSuchElementException.class)
    public void showOrdersWithDetailsNoOrders() {

        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.of(users.get(0)));
        when(orderDAOMock.getByUserAndStatus(1, Order.Status.CREATED)).thenReturn(new ArrayList<>());

        service.orderDetails("Ivan", Order.Status.CREATED);
    }


    @Test
    public void cancelOrder() throws Exception {
        when(orderDAOMock.remove(anyLong())).thenReturn(true);

        boolean check = service.cancelOrder(anyLong());
        verify(orderDAOMock, times(1)).remove(anyLong());
        verifyNoMoreInteractions(orderDAOMock);

        assertTrue(check);
    }


    @Test(expected = NoSuchElementException.class)
    public void makeOrderNoUser() throws Exception {

        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.ofNullable(null));
        service.makeOrder("Ivan", userChoice);
    }

    @Test
    public void makeOrderValid() {

        when(userDAOMock.getByName("Ivan")).thenReturn(Optional.of(users.get(0)));
        when(orderDAOMock.create(new Order(0, users.get(0).getId(), LocalDateTime.now(), 0, Order.Status.CREATED)))
                .thenReturn(true);

        when(orderDAOMock.getAll()).thenReturn(orders);
        when(dishDAOMock.getByName("MOJITO")).thenReturn(Optional.of(dishes.get(0)));
        when(dishDAOMock.getByName("BURRITO")).thenReturn(Optional.of(dishes.get(1)));

        DishOrder dishOrder1 = new DishOrder(0, orders.get(0).getId(),
                dishes.get(0).getId(), amount_1, amount_1 * dishes.get(0).getPrice());
        when(dishOrderDAOMock.create(dishOrder1)).thenReturn(true);

        DishOrder dishOrder2 = new DishOrder(0, orders.get(0).getId(),
                dishes.get(1).getId(), amount_2, amount_2 * dishes.get(1).getPrice());
        when(dishOrderDAOMock.create(dishOrder2)).thenReturn(true);

        when(orderDAOMock.getById(orders.get(0).getId())).thenReturn(Optional.of(orders.get(0)));
        when(orderDAOMock.update(orders.get(0))).thenReturn(true);

        service.makeOrder("Ivan", userChoice);

        verify(userDAOMock, times(2)).getByName("Ivan");
        verify(orderDAOMock, times(1)).create(any());
        verify(orderDAOMock, times(1)).getAll();
        verify(dishDAOMock, times(1)).getByName("MOJITO");
        verify(dishDAOMock, times(1)).getByName("BURRITO");
        verify(dishOrderDAOMock, times(1)).create(dishOrder1);
        verify(dishOrderDAOMock, times(1)).create(dishOrder2);
        verify(orderDAOMock, times(1)).getById(orders.get(0).getId());
        verify(orderDAOMock, times(1)).update(orders.get(0));

        verifyNoMoreInteractions(userDAOMock, orderDAOMock, dishDAOMock, dishOrderDAOMock);
    }
}
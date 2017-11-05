package com.service;

import com.dao.OrderDAO;
import com.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusServiceTest {

    @Mock private OrderDAO orderDAO;

    private static final OrderStatusService orderStatusService = OrderStatusService.getInstance();
    private static Optional<Order> order;

    @Before
    public void init() {

        when(orderDAO.getById(anyInt())).thenReturn(Optional.of(new Order(1, 1,
                LocalDateTime.of(2017, 10, 25, 13, 54, 19),
                475, Order.Status.CREATED)));


        when(orderDAO.update(any(Order.class))).thenReturn(true);


        order = orderDAO.getById(anyInt());
        orderStatusService.setOrderDAO(orderDAO);

    }

    @Test
    public void confirmOrder() throws Exception {
        orderStatusService.confirmOrder(anyInt());
        verifyMethod();
        assertEquals(Order.Status.CONFIRMED, order.get().getStatus());
    }


    @Test
    public void payOrder() throws Exception {
        orderStatusService.payOrder(anyInt());
        verifyMethod();
        assertEquals(Order.Status.PAID, order.get().getStatus());
    }

    @Test
    public void makeBill() throws Exception {
        orderStatusService.makeBill(anyInt());
        verifyMethod();
        assertEquals(Order.Status.READY, order.get().getStatus());


    }

    private void verifyMethod() {
        verify(orderDAO, times(2)).getById(anyInt());
        verify(orderDAO, times(1)).update(any(Order.class));
        verifyNoMoreInteractions(orderDAO);
    }


}
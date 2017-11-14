package com.service;

import com.dao.OrderDAO;
import com.model.Order;

import java.util.NoSuchElementException;
import java.util.Optional;


public class OrderStatusService {

    private OrderDAO orderDAO = OrderDAO.getInstance();
    private static OrderStatusService instance;

    private OrderStatusService() {
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public static OrderStatusService getInstance() {
        if (instance == null) {
            instance = new OrderStatusService();
        }
        return instance;
    }

    public void confirmOrder(long id) {
        Optional<Order> order = orderDAO.getById(id);
        if (order.isPresent()) {
            order.get().setStatus(Order.Status.CONFIRMED);
            orderDAO.update(order.get());
        }
        else{
            throw new NoSuchElementException("There is no such order!");
        }

    }

    public void payOrder(long id) {
        Optional<Order> order = orderDAO.getById(id);
        if (order.isPresent()) {
            order.get().setStatus(Order.Status.PAID);
            orderDAO.update(order.get());
        }
        else{
            throw new NoSuchElementException("There is no such order!");
        }
    }

    public void makeBill(long id) {
        Optional<Order> order = orderDAO.getById(id);
        if (order.isPresent()) {
            order.get().setStatus(Order.Status.READY);
            orderDAO.update(order.get());
        }
        else{
            throw new NoSuchElementException("There is no such order!");
        }
    }
}

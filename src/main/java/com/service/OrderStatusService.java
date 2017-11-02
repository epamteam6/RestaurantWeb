package com.service;

import com.dao.OrderDAO;
import com.model.Order;

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


    public void confirmOrder(int id) {
        Order order = orderDAO.getById(id).get();
        order.setStatus(Order.Status.CONFIRMED);
        orderDAO.update(order);
    }

    public void payOrder(int id) {
        Order order = orderDAO.getById(id).get();
        order.setStatus(Order.Status.PAID);
        orderDAO.update(order);
    }

    public void makeBill(int id) {
        Order order = orderDAO.getById(id).get();
        order.setStatus(Order.Status.READY);
        orderDAO.update(order);
    }
}

package com.service;

import com.dao.OrderDAO;
import com.model.Order;

public class OrderStatusService {

    public void confirmOrder(int id){
        Order order = OrderDAO.getInstance().getById(id);
        order.setSt(Order.status.CONFIRMED);
        OrderDAO.getInstance().update(order);
    }

    public void payOrder(int id){
        Order order = OrderDAO.getInstance().getById(id);
        order.setSt(Order.status.PAID);
        OrderDAO.getInstance().update(order);
    }

    public void makeBill(int id){
        Order order = OrderDAO.getInstance().getById(id);
        order.setSt(Order.status.READY);
        OrderDAO.getInstance().update(order);
    }
}

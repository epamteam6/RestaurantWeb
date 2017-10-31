package com.service;

import com.dao.OrderDAO;
import com.model.Order;

public class OrderStatusService {

    public void confirmOrder(int id){
        Order order = OrderDAO.getInstance().getById(id);
        order.setSt(Order.status.CONFIRMED);
        OrderDAO.getInstance().update(OrderDAO.getInstance().getById(id));
    }
}

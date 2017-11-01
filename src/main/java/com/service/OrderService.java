package com.service;

import com.dao.DishDAO;
import com.dao.DishOrderDAO;
import com.dao.OrderDAO;
import com.dao.UserDAO;
import com.model.DishOrder;
import com.model.Order;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderService {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private DishDAO dishDAO = DishDAO.getInstance();
    private DishOrderDAO dishOrderDAO = DishOrderDAO.getInstance();
    private static OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void makeOrder(String userName, Map<String, Integer> dishNamesAndAmount) {
        long userID = userDAO.getByName(userName).get().getId();
        orderDAO.create(new Order(0, userID, LocalDateTime.now(), 0, Order.Status.CREATED));

        long orderID = 0;
        for (Order order : orderDAO.getAll()) {
            if (order.getUserId() == userID && order.getStatus().equals(Order.Status.CREATED)) {
                orderID = order.getId();
            }
        }
        final long finalOrderID = orderID;

        long [] total_sum = {0};
        dishNamesAndAmount.forEach((dishName, amount) -> {
            long dishId = dishDAO.getByName(dishName).get().getId();
            long price = dishDAO.getByName(dishName).get().getPrice();
            total_sum[0]+=amount*price;
            dishOrderDAO.add(
                    new DishOrder(0, finalOrderID,
                            dishId, amount, amount * price));
        });


        orderDAO.getById(finalOrderID).get().setTotalSum(total_sum[0]);
    }

    public void makeBill(int id) {
        Order order = orderDAO.getById(id).get();
        order.setStatus(Order.Status.READY);
        orderDAO.update(order);
    }

}

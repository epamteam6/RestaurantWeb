package com.service;

import com.dao.*;
import com.model.*;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private DishDAO dishDAO = DishDAO.getInstance();
    private DishOrderDAO dishOrderDAO = DishOrderDAO.getInstance();
    private static OrderService instance;

    private OrderService() {
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public void setDishOrderDAO(DishOrderDAO dishOrderDAO) {
        this.dishOrderDAO = dishOrderDAO;
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public boolean cancelOrder(Long id) {
        return orderDAO.remove(id);
    }

    public void makeOrder(String userName, Map<String, Long> dishNamesAndAmount) {
        if (!userDAO.getByName(userName).isPresent()) {
            throw new NoSuchElementException("There is no such user!");
        }
        long userID = userDAO.getByName(userName).get().getId();
        orderDAO.create(new Order(0, userID, LocalDateTime.now(), 0, Order.Status.CREATED));

        long orderID = 0;
        for (Order order : orderDAO.getAll()) {
            if (order.getUserId() == userID && order.getStatus().equals(Order.Status.CREATED)) {
                orderID = order.getId();
            }
        }

        long totalSum = 0;
        for (Map.Entry<String, Long> pair : dishNamesAndAmount.entrySet())
        {
            long amount = pair.getValue();
            Optional<Dish> dish = dishDAO.getByName(pair.getKey());
            if (dish.isPresent()) {
                long dishId = dish.get().getId();
                long price = dish.get().getPrice();
                totalSum += amount * price;
                dishOrderDAO.create(new DishOrder(0, orderID,
                        dishId, amount, amount * price));

            } else throw new NoSuchElementException("There is no such dishname!");
        }

        Order order = orderDAO.getById(orderID).get();
        order.setTotalSum(totalSum);
        orderDAO.update(order);
    }


    public Map<Long, Map<String, Long>> orderDetails(String username, Order.Status status) {

        Optional<User> user = userDAO.getByName(username);
        if (!user.isPresent()) {
            throw new NoSuchElementException("There is no such user!");
        }

        long userId = user.get().getId();
        List<Order> orders = new ArrayList<>();
        for (Order order : orderDAO.getAll()) {

            if (order.getUserId() == userId && order.getStatus() == status) {
                orders.add(order);
            }
        }

        if (orders.size() < 1) {
            throw new NoSuchElementException("There is no order with this status!");
        }


        Map<Long, Map<String, Long>> result = new HashMap<>();
        Map<String, Long> orderAndAmount;
        List<DishOrder> dishOrders = dishOrderDAO.getAll();

        for (Order order : orders) {
            orderAndAmount = new HashMap<>();

            for (DishOrder dishOrder : dishOrders) {
                if (dishOrder.getOrderId() == order.getId()) {
                    String dishname = dishDAO.getById(dishOrder.getDishId()).get().getDishname();
                    long amount = dishOrder.getDishAmount();

                    orderAndAmount.put(dishname, amount);
                }
            }

            result.put(order.getId(), orderAndAmount);
        }

        return result;
    }
}

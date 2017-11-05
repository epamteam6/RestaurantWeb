package com.service;

import com.dao.*;
import com.model.*;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();
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

    public void setDishTypeDAO(DishTypeDAO dishTypeDAO) {
        this.dishTypeDAO = dishTypeDAO;
    }

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public void setDishOrderDAO(DishOrderDAO dishOrderDAO) {
        this.dishOrderDAO = dishOrderDAO;
    }

    /*{
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "root");
            userDAO.setDataSource(dataSource);
            orderDAO.setDataSource(dataSource);
            dishDAO.setDataSource(dataSource);
            dishOrderDAO.setDataSource(dataSource);
            dishTypeDAO.setDataSource(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

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
        Iterator it = dishNamesAndAmount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            long amount = pair.getValue();
            Optional<Dish> dish = dishDAO.getByName(pair.getKey());
            if (dish.isPresent()) {
                long dishId = dish.get().getId();
                long price = dish.get().getPrice();
                totalSum += amount * price;
                dishOrderDAO.create(new DishOrder(0, orderID,
                        dishId, amount, amount * price));

                it.remove(); // avoids a ConcurrentModificationException
            } else throw new NoSuchElementException("There is no such dishname!");
        }

        Order order = orderDAO.getById(orderID).get();
        order.setTotalSum(totalSum);
        orderDAO.update(order);

    }

    public Map<String, Map<String, Long>> getMenu() {
        Map<String, Map<String, Long>> menu = new HashMap<>();
        List<Dish> allDishes = dishDAO.getAll();
        List<DishType> allDishTypes = dishTypeDAO.getAll();

        Set<Long> allDishTypesNumbers = new HashSet<>();
        for (DishType dishType : allDishTypes) {
            allDishTypesNumbers.add(dishType.getId());
        }

        for (Long type : allDishTypesNumbers) {
            Map<String, Long> submenu = new HashMap<>();
            Optional<DishType> dishType = dishTypeDAO.getById(type);
            if (dishType.isPresent()) {
                menu.put(dishType.get().getDishType(), submenu);
            } else throw new NoSuchElementException("There is no such dishname type!");
        }

        for (Dish dish : allDishes) {
            if (dishTypeDAO.getById(dish.getDishTypeId()).isPresent()) {
                String dishTypeName = dishTypeDAO.getById(dish.getDishTypeId()).get().getDishType();
                Map<String, Long> submenu = menu.get(dishTypeName);
                submenu.put(dish.getDishname(), dish.getPrice());
                menu.put(dishTypeName, submenu);
            } else throw new NoSuchElementException("There is no such dishname type!");
        }
        return menu;
    }

    public Map<LocalDateTime, Map<String, Long>> showOrdersWithDetails(String username, Order.Status status) {

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


        Map<LocalDateTime, Map<String, Long>> result = new HashMap<>();
        Map<String, Long> orderDetails;
        List<DishOrder> dishOrders = dishOrderDAO.getAll();
        for (Order order : orders) {
            orderDetails = new HashMap<>();

            for (DishOrder dishOrder : dishOrders) {
                if (dishOrder.getDishId() == order.getId()) {
                    String dishname = dishDAO.getById(dishOrder.getDishId()).get().getDishname();
                    long amount = dishOrder.getDishAmount();

                    orderDetails.put(dishname, amount);
                }
            }

            result.put(order.getDateTime(), orderDetails);
        }

        return result;
    }
}

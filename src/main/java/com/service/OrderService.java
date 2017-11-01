package com.service;

import com.dao.DishDAO;
import com.dao.DishOrderDAO;
import com.dao.OrderDAO;
import com.dao.UserDAO;
import com.model.DishOrder;
import com.model.Order;
import com.mysql.jdbc.Driver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

public class OrderService {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private DishDAO dishDAO = DishDAO.getInstance();
    private DishOrderDAO dishOrderDAO = DishOrderDAO.getInstance();
    private static OrderService instance;

    private OrderService() {
    }

    {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource(new Driver(),
                    "jdbc:mysql://localhost:3306/food?serverTimezone=UTC", "root", "root");
            userDAO.setDataSource(dataSource);
            orderDAO.setDataSource(dataSource);
            dishDAO.setDataSource(dataSource);
            dishOrderDAO.setDataSource(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void makeOrder(String userName, Map<String, Long> dishNamesAndAmount) {

        long userID = userDAO.getByName(userName).get().getId();
        orderDAO.create(new Order(0, userID, LocalDateTime.now(), 0, Order.Status.CREATED));

        long orderID = 0;
        for (Order order : orderDAO.getAll()) {
            if (order.getUserId() == userID && order.getStatus().equals(Order.Status.CREATED)) {
                orderID = order.getId();
            }
        }
        final long finalOrderID = orderID;

        long total_sum = 0;
        Iterator it = dishNamesAndAmount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            long amount = pair.getValue();
            long dishId = dishDAO.getByName(pair.getKey()).get().getId();
            long price = dishDAO.getByName(pair.getKey()).get().getPrice();
            total_sum += amount * price;
            System.out.println(total_sum);
            dishOrderDAO.add(new DishOrder(0, finalOrderID,
                    dishId, amount, amount * price));

            it.remove(); // avoids a ConcurrentModificationException

        }
        Order order = orderDAO.getById(finalOrderID).get();
        order.setTotalSum(total_sum);
        orderDAO.update(order);
    }


}

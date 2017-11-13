package com.controller.admin;

import com.model.Order;
import com.model.User;
import com.service.OrderService;
import com.service.OrderStatusService;
import com.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillCreationServlet extends HttpServlet {

    private Map<String, Map<String, Long>> menu;
    private UserService userService = UserService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private OrderStatusService orderStatusService = OrderStatusService.getInstance();
    private List<List> usersOrders;
    private List<Long> orderNumbers;
    private List<User> allUsers;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        allUsers = userService.getUserDAO().getAll();
        System.out.println(allUsers);

        getConfirmedOrders();

        request.setAttribute("usersOrders", usersOrders);
        request.setAttribute("orderNumbers", orderNumbers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_bill_creation.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin_bill_creation.jsp").include(request, response);


        System.out.println(orderNumbers);

        Boolean isBillButtonClicked = request.getParameter("Bill") != null;
        Boolean isCancelButtonClicked = request.getParameter("Cancel") != null;

        Boolean isAnyOptionChosen = false;
        for (Long number : orderNumbers) {
            Boolean checked = request.getParameter(number.toString()) != null;
            if (checked) {
                isAnyOptionChosen = true;
                if (isBillButtonClicked) {
                    orderStatusService.makeBill(number);
                }
                if (isCancelButtonClicked) {
                    orderService.cancelOrder(number);
                }
            }
        }


        getConfirmedOrders();
        request.setAttribute("usersOrders", usersOrders);
        request.setAttribute("orderNumbers", orderNumbers);

        if(!isAnyOptionChosen){
            request.setAttribute("message", "You didn't choose any orders!");
        }

        else if (isBillButtonClicked) {
            request.setAttribute("message", "You made bill to selected orders!");
        }

        else if (isCancelButtonClicked) {
            request.setAttribute("message", "You canceled selected orders!");
        }

        getServletContext().getRequestDispatcher("/admin_bill_creation.jsp").forward(request, response);


    }

    private void getConfirmedOrders() {
        usersOrders = new ArrayList<>();
        orderNumbers = new ArrayList<>();

        for (User user : allUsers) {
            Map<Long, Map<String, Long>> ordersDetails = orderService.orderDetails(user.getUserName(), Order.Status.CONFIRMED);
            if (!ordersDetails.isEmpty()) {
                for (Long number : ordersDetails.keySet()) {
                    orderNumbers.add(number);
                    List details = new ArrayList();
                    details.add(user.getUserName());
                    details.add(number);
                    details.add(ordersDetails.get(number));
                    details.add(orderService.getOrderDAO().getById(number).get().getTotalSum());
                    usersOrders.add(details);
                }
            }
        }
    }
}
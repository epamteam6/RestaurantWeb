package com.service;

import org.junit.Test;

public class OrderServiceTest {
    @Test
    public void getMenu() throws Exception {
        System.out.println(OrderService.getInstance().getMenu());
    }

}
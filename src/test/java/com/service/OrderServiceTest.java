package com.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceTest {
    @Test
    public void makeOrder() throws Exception {
        Map<String, Long> map = new HashMap<>();
        map.put("Beer", 1L);
        map.put("Borsch", 5L);
        OrderService.getInstance().makeOrder("Zaal", map);
    }

}
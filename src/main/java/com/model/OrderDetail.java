package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDetail {

    private int id;
    private int orderId;
    private int dishId;
    private int dishAmount;
    private int dishSum;
}

package com.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private int id;
    private int userId;
    private LocalDateTime dateTime;
    private int totalSum;
    private status st;

    private enum status {
        CREATED, CONFIRMED, READY, PAID;
    }
}

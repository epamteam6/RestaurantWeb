package com.model;

import lombok.Data;

@Data
public class   User {
    private int id;
    private String userName;
    private String passwordHash;
    private boolean isAdmin;
}

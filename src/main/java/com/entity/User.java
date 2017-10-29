package com.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User
{
    @Getter @Setter(AccessLevel.PRIVATE)
    private String userName;
    @Getter @Setter
    private boolean isAdmin;

    public User(String userName, boolean isAdmin)
    {
        this.userName = userName;
        this.isAdmin = isAdmin;
    }
}

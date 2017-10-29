package com.entity;

import lombok.*;

@AllArgsConstructor
@Data
public class User
{
    @Getter @Setter(AccessLevel.PRIVATE)
    private int id;
    @Getter @Setter(AccessLevel.PRIVATE)
    private String userName;
    @Getter @Setter
    private boolean isAdmin;
}

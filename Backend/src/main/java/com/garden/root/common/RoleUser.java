package com.garden.root.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RoleUser {
    CUSTOMER(0),
    ADMIN(1);
    
    @Getter
    private final Integer code;
}

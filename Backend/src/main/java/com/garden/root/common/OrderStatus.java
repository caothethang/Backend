package com.garden.root.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    ACCEPTED(1),
    REJECTED(0),
    WAITING(2);
    
    @Getter
    private final Integer code;
}

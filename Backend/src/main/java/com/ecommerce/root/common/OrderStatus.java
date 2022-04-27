package com.ecommerce.root.common;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    WAITING(0),
    APPROVED(1),
    REJECTED(2);
    
    @Getter
    private final Integer code;
}

package com.ecommerce.root.common;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    WAITING(0),
    APPROVED(1),
    REJECTED(2),
    CUSTOMER_RECEIVED_SUCCESS(3),
    CUSTOMER_RETURNS(4);
    
    @Getter
    private final Integer code;
}

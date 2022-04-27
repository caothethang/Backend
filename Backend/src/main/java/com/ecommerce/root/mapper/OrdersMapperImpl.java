package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.Orders;
import com.ecommerce.root.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapperImpl implements OrderMapper{

    @Override
    public OrderResponse toResponse(Orders orders) {
        CustomerInfo customerInfo = orders.getCustomerInfo();
        return OrderResponse.builder()
                .id(orders.getId())
                .phoneNumber(customerInfo.getPhone())
                .price(orders.getTotalPrice())
                .address(customerInfo.getAddress())
                .status(orders.getStatus())
                .createdAt(orders.getCreatedAt().getTime())
                .build();
    }
}

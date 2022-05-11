package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.Orders;
import com.ecommerce.root.entity.User;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
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
                .userName(orders.getUserName())
                .fullName(customerInfo.getFullName())
                .createdAt(orders.getCreatedAt().getTime())
                .build();
    }
}

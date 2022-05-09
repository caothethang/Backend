package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.OrderDetails;
import com.ecommerce.root.response.OrderDetailListResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapperImpl implements OrderDetailMapper{

    @Override
    public OrderDetailListResponse toResponse(OrderDetails orderDetails) {
        return OrderDetailListResponse.builder()
                .color(orderDetails.getColor())
                .id(orderDetails.getId())
                .price(orderDetails.getPrice())
                .quantity(orderDetails.getQuantity())
                .categoryName(orderDetails.getProduct().getCategory().getName())
                .size(orderDetails.getSize())
                .color(orderDetails.getColor())
                .productName(orderDetails.getProduct().getName())
                .build();
    }
}

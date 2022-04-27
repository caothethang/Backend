package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.Orders;
import com.ecommerce.root.response.OrderResponse;

public interface OrderMapper {
    OrderResponse toResponse(Orders orders);
}

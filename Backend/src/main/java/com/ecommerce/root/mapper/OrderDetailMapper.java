package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.OrderDetails;
import com.ecommerce.root.response.OrderDetailListResponse;

public interface OrderDetailMapper {

    OrderDetailListResponse toResponse(OrderDetails orderDetails);
}

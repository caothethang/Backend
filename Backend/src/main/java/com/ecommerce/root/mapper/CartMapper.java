package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.CartItem;
import com.ecommerce.root.response.CartResponse;

public interface CartMapper {

    CartResponse toResponse(CartItem cartItem);
}

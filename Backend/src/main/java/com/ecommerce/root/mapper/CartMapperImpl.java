package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.CartItem;
import com.ecommerce.root.response.CartResponse;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper{
    @Override
    public CartResponse toResponse(CartItem cartItem) {
        return null;
    }
}

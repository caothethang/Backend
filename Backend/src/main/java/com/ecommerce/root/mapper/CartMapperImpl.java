package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.CartItem;
import com.ecommerce.root.response.CartResponse;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper{
    @Override
    public CartResponse toResponse(CartItem cartItem) {
        return CartResponse.builder()
                .cartItemId(cartItem.getId())
                .categoryName(cartItem.getCategoryName())
                .productImage(cartItem.getImage())
                .productName(cartItem.getProductName())
                .quantity(cartItem.getQuantity())
                .size(cartItem.getSize())
                .price(cartItem.getPrice())
                .build();
    }
}

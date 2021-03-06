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
                .productId(cartItem.getProductId())
                .productImage(cartItem.getImage())
                .productQuantity(cartItem.getProductQuantity())
                .productName(cartItem.getProductName())
                .quantity(cartItem.getQuantity())
                .size(cartItem.getSize())
                .color(cartItem.getColor())
                .price(cartItem.getPrice())
                .build();
    }
}

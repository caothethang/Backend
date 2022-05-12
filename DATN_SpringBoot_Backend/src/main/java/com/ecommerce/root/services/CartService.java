package com.ecommerce.root.services;

import com.ecommerce.root.request.AddToCartRequest;
import com.ecommerce.root.request.UpdateCartRequest;
import com.ecommerce.root.response.BaseResponse;

import java.util.List;

public interface CartService {
    
    BaseResponse addToCart(AddToCartRequest request);

    Object getCart(Long userId);

    Object removeCartItem(Long cartItemId);

    Object updateCart(List<UpdateCartRequest> updateCartRequest);
}

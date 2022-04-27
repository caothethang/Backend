package com.ecommerce.root.services;

import com.ecommerce.root.request.AddToCartRequest;
import com.ecommerce.root.response.BaseResponse;

public interface CartService {
    
    BaseResponse addToCart(AddToCartRequest request);

    Object getCart(Long userId);
}

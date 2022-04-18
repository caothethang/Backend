package com.ecommerce.root.services;

import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.response.BaseResponse;

public interface OrderService {
    
    Object buyProduct(BuyProductRequest request);
    
}

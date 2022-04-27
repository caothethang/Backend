package com.ecommerce.root.services;

import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.OrderHistoryResponse;

import java.util.List;

public interface OrderService {
    
    Object buyProduct(BuyProductRequest request);
    
    Object getOrderHistory(OrderHistoryRequest request);

    Object getListOrderRequest();
}

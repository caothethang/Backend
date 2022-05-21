package com.ecommerce.root.services;

import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderHistoryRequest;

public interface OrderService {
    
    Object buyProduct(BuyProductRequest request);
    
    Object getOrderHistory(OrderHistoryRequest request);

    Object getListOrderRequest();

    Object approveOrder(Long orderId,Integer status);

    Object getOrderDetail(Long orderId);
    
    Object deleteOrder(Long orderId);
    
    Object calculateTurnOver(Long startDate, Long endDate);
    
    Object customerActionOrder(Long orderId, Integer status);
}

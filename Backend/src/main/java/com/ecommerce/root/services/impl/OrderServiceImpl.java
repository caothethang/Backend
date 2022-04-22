package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.OrderStatus;
import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.OrderDetails;
import com.ecommerce.root.entity.Orders;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.repositories.CustomerInforRepository;
import com.ecommerce.root.repositories.OrderDetailRepository;
import com.ecommerce.root.repositories.OrderRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderDetailRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.response.OrderHistoryResponse;
import com.ecommerce.root.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final ProductRepository productRepository;
    private final CustomerInforRepository customerInforRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    
    @Override
    public Object buyProduct(BuyProductRequest request) {
        
        CustomerInfo customerInfo = CustomerInfo.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        customerInfo = customerInforRepository.save(customerInfo);
        Orders orders = Orders.builder()
                .receivedAddress(request.getReceivedAddress())
                .status(OrderStatus.WAITING.getCode())
                .customerInfo(customerInfo)
                .build();
        orders = orderRepository.save(orders);
        List<OrderDetailRequest> orderDetailRequestList = request.getOrderDetailRequests();
        for (OrderDetailRequest orderDetailRequest : orderDetailRequestList) {
            Long productId = orderDetailRequest.getProductId();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                OrderDetails orderDetails = OrderDetails.builder()
                        .product(product)
                        .size(orderDetailRequest.getSize())
                        .quantity(orderDetailRequest.getQuantity())
                        .gender(orderDetailRequest.getGender())
                        .orders(orders)
                        .build();
                orderDetailRepository.save(orderDetails);
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không tìm thấy sản phẩm !");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Mua hàng thành công");
    }
    
    @Override
    public List<OrderHistoryResponse> getOrderHistory(OrderHistoryRequest request) {
        String email = request.getEmail();
        List<CustomerInfo> customerInfos = customerInforRepository.findAllByEmail(email);
        List<OrderHistoryResponse> orderHistoryResponses = new ArrayList<>();
        customerInfos.forEach(customerInfo -> {
            List<Orders> ordersList = customerInfo.getOrders();
            ordersList.forEach(orders -> {
                List<OrderDetails> orderDetails= orders.getOrderDetails();
                orderDetails.forEach(orderDetail ->{
                    OrderHistoryResponse orderHistoryResponse = OrderHistoryResponse.builder()
                            .productImage(orderDetail.getProduct().getImage())
                            .gender(orderDetail.getGender())
                            .quantity(orderDetail.getQuantity())
                            .size(orderDetail.getSize())
                            .build();
                });
                
            });
        });
        return null;
    }
}

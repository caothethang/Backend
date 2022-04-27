package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.OrderStatus;
import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.OrderDetails;
import com.ecommerce.root.entity.Orders;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.mapper.OrderMapper;
import com.ecommerce.root.repositories.CustomerInforRepository;
import com.ecommerce.root.repositories.OrderDetailRepository;
import com.ecommerce.root.repositories.OrderRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderDetailRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.OrderDetailListResponse;
import com.ecommerce.root.response.OrderHistoryResponse;
import com.ecommerce.root.response.OrderResponse;
import com.ecommerce.root.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final CustomerInforRepository customerInforRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;

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
                .receivedAddress(request.getAddress())
                .status(OrderStatus.WAITING.getCode())
                .totalPrice(request.getTotalPrice())
                .createdAt(new Timestamp(System.currentTimeMillis()))
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
                        .color(orderDetailRequest.getColor())
                        .price(orderDetailRequest.getPrice())
                        .quantity(orderDetailRequest.getQuantity())
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .orders(orders)
                        .build();
                orderDetailRepository.save(orderDetails);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không tìm thấy sản phẩm !");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Mua hàng thành công");
    }

    @Override
    public Object getOrderHistory(OrderHistoryRequest request) {
        String email = request.getEmail();
        if (Objects.isNull(email)) {
            return BaseResponse.builder()
                    .data("Không tìm thấy email")
                    .status(500)
                    .build();
        }
        List<CustomerInfo> customerInfos = customerInforRepository.findAllByEmail(email);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        customerInfos.forEach(customerInfo -> {
            List<Orders> ordersList = customerInfo.getOrders();
            ordersList.forEach(orders -> {
                orderResponseList.add(orderMapper.toResponse(orders));
            });
        });
        return BaseResponse.builder()
                .status(200)
                .data(orderResponseList)
                .build();
    }

    @Override
    public Object getListOrderRequest() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Orders orders : ordersList) {
            orderResponseList.add(orderMapper.toResponse(orders));
        }
        return BaseResponse.builder()
                .status(200)
                .data(orderResponseList)
                .build();
    }

//    public Object getOrderRequestDetail(Long orderId){
//        Optional<Orders> optionalOrders = orderRepository.findById(orderId);
//        List<OrderDetailListResponse> orderDetailListResponses = new ArrayList<>();
//        if (optionalOrders.isPresent()){
//            Orders orders = optionalOrders.get();
//            List<OrderDetails> orderDetailsList = orders.getOrderDetails();
//            for (OrderDetails e: orderDetailsList) {
//
//            }
//        }
//    }

    @Override
    public Object approveOrder(Long orderId, Integer status) {
        Optional<Orders> optionalOrders = orderRepository.findById(orderId);
        if (optionalOrders.isPresent()) {
            if (status == 0) {
                Orders orders = optionalOrders.get();
                orders.setStatus(OrderStatus.REJECTED.getCode());
                orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                orderRepository.save(orders);
                return BaseResponse.builder()
                        .status(200)
                        .data("Từ chối đơn hàng thành công")
                        .build();
            }
            if (status == 1) {
                Orders orders = optionalOrders.get();
                orders.setStatus(OrderStatus.APPROVED.getCode());
                orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                orderRepository.save(orders);
                return BaseResponse.builder()
                        .status(200)
                        .data("Duyệt đơn hàng thành công")
                        .build();
            }
        }
        return BaseResponse.builder()
                .status(500)
                .data("Không tìm thấy orders")
                .build();
    }
}

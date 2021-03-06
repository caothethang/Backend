package com.ecommerce.root.services.impl;

import com.ecommerce.root.common.OrderStatus;
import com.ecommerce.root.entity.*;
import com.ecommerce.root.mapper.OrderDetailMapper;
import com.ecommerce.root.mapper.OrderMapper;
import com.ecommerce.root.repositories.*;
import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderDetailRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.OrderDetailListResponse;
import com.ecommerce.root.response.OrderHistoryResponse;
import com.ecommerce.root.response.OrderResponse;
import com.ecommerce.root.services.CartService;
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
    private final OrderDetailMapper orderDetailMapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    @Override
    public Object buyProduct(BuyProductRequest request) {

        CustomerInfo customerInfo = CustomerInfo.builder()
                .username(request.getUserName())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phone(request.getPhone())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        customerInfo = customerInforRepository.save(customerInfo);
        Orders orders = Orders.builder()
                .userName(request.getUserName())
                .receivedAddress(request.getAddress())
                .status(OrderStatus.WAITING.getCode())
                .totalPrice(request.getTotalPrice())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .customerInfo(customerInfo)
                .build();
        orders = orderRepository.save(orders);
        User user = userRepository.findByUsername(request.getUserName());
        Cart cart = cartRepository.findByUserId(user.getId());
        List<CartItem> cartItemList = cart.getCartItems();
        cartItemList.forEach(cartItem -> {
            cartService.removeCartItem(cartItem.getId());
        });
        List<OrderDetailRequest> orderDetailRequestList = request.getOrderDetailRequests();
        for (OrderDetailRequest orderDetailRequest : orderDetailRequestList) {
            Long productId = orderDetailRequest.getProductId();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (orderDetailRequest.getQuantity() > product.getQuantity()){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("V?????t qu?? s??? l?????ng trong kho");
                }
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
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kh??ng t??m th???y s???n ph???m !");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Mua h??ng th??nh c??ng");
    }

    @Override
    public Object getOrderHistory(OrderHistoryRequest request) {
        String userName = request.getUserName();
        if (Objects.isNull(userName)) {
            return BaseResponse.builder()
                    .data("Kh??ng t??m th???y username")
                    .status(500)
                    .build();
        }
        List<CustomerInfo> customerInfos = customerInforRepository.findAllByUsername(userName);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        customerInfos.forEach(customerInfo -> {
            List<Orders> ordersList = orderRepository.findAllByCustomerInfo(customerInfo);
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

    @Override
    public Object getOrderDetail(Long orderId){
        try {
            Optional<Orders> optionalOrders = orderRepository.findById(orderId);
            List<OrderDetailListResponse> orderDetailListResponses = new ArrayList<>();
            if (optionalOrders.isPresent()){
                Orders orders = optionalOrders.get();
                List<OrderDetails> orderDetailsList = orderDetailRepository.findAllByOrders(orders);
                for (OrderDetails e: orderDetailsList) {
                    orderDetailListResponses.add(orderDetailMapper.toResponse(e));
                }
                OrderResponse orderResponse = orderMapper.toResponse(orders);
                orderResponse.setOrderDetailListResponseList(orderDetailListResponses);
                return BaseResponse.builder()
                        .status(200)
                        .data(orderResponse)
                        .build();
            }
            return BaseResponse.builder()
                    .status(500)
                    .data("No data")
                    .build();
        } catch (Exception e) {
            return BaseResponse.builder()
                    .status(500)
                    .data("Kh??ng th??? view detail")
                    .build();
        }
    }
    
    @Override
    public Object deleteOrder(Long orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
       Orders orders1 = orders.get();
       orders1.setStatus(OrderStatus.REJECTED.getCode());
        orderRepository.save(orders1);
        return BaseResponse.builder()
                .status(200)
                .data("Xo?? th??nh c??ng")
                .build();
    }
    
    @Override
    public Object calculateTurnOver(Long startDate, Long endDate) {
        List<Orders> ordersList = orderRepository.findAllByCreatedAtGreaterThanAndCreatedAtLessThanAndStatus(new Timestamp(startDate),new Timestamp(endDate),OrderStatus.CUSTOMER_RETURNS.getCode());
        List<OrderResponse> orderResponseList = new ArrayList<>();
        Long turnOver = 0L;
        for (Orders orders : ordersList) {
            turnOver += orders.getTotalPrice();
            orderResponseList.add(orderMapper.toResponse(orders));
        }
        return BaseResponse.builder()
                .status(200)
                .totalTurnover(turnOver)
                .data(orderResponseList)
                .build();
    }
    
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
                        .data("T??? ch???i ????n h??ng th??nh c??ng")
                        .build();
            }
            if (status == 1) {
                Orders orders = optionalOrders.get();
    
                List<OrderDetails> orderDetailsList = orderDetailRepository.findAllByOrders(orders);
    
                orderDetailsList.forEach(orderDetails -> {
                    Product product = orderDetails.getProduct();
                    product.setQuantity(product.getQuantity() - orderDetails.getQuantity());
                    productRepository.save(product);
                });
                
                orders.setStatus(OrderStatus.APPROVED.getCode());
                orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                
                orderRepository.save(orders);
                return BaseResponse.builder()
                        .status(200)
                        .data("Duy???t ????n h??ng th??nh c??ng")
                        .build();
            }
        }
        return BaseResponse.builder()
                .status(500)
                .data("Kh??ng t??m th???y orders")
                .build();
    }
    
    public Object customerActionOrder(Long orderId, Integer status) {
        Optional<Orders> optionalOrders = orderRepository.findById(orderId);
        if (optionalOrders.isPresent()) {
            if (status == 0) {
                Orders orders = optionalOrders.get();
                orders.setStatus(OrderStatus.CUSTOMER_RETURNS.getCode());
                orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                orderRepository.save(orders);
                return BaseResponse.builder()
                        .status(200)
                        .data("Kh??ch h??ng ho??n tr??? ????n h??ng")
                        .build();
            }
            if (status == 1) {
                Orders orders = optionalOrders.get();
                orders.setStatus(OrderStatus.CUSTOMER_RECEIVED_SUCCESS.getCode());
                orders.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                orderRepository.save(orders);
                return BaseResponse.builder()
                        .status(200)
                        .data("Kh??ch h??ng nh???n h??ng th??nh c??ng")
                        .build();
            }
        }
        return BaseResponse.builder()
                .status(500)
                .data("Kh??ng t??m th???y b???n ghi")
                .build();
    }
}

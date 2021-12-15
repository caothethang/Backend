package com.garden.root.controller;

import com.garden.root.request.OrderRequest;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    private final UserService userService;
    
    public OrderController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/")
    public BaseResponse createOrder(@RequestBody OrderRequest orderRequest){
//        User u = userService.getUserById(orderRequest.getUserId()).getData();
//        Order o = Order.builder()
//                .orderItems(orderRequest.getItem())
//                .user(u)
//                .build();
        return null;
    }
}

package com.garden.root.controller;

import com.garden.root.common.OrderStatus;
import com.garden.root.dto.OrderDTO;
import com.garden.root.entity.OrderList;
import com.garden.root.mapper.OrderMapper;
import com.garden.root.repositories.OrderRepository;
import com.garden.root.request.OrderRequest;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    private final UserService userService;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    
    public OrderController(UserService userService, OrderRepository repository, OrderMapper mapper) {
        this.userService = userService;
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @PostMapping("/")
    public BaseResponse createOrder(@RequestBody OrderRequest orderRequest) {
        OrderList orderList = OrderList.builder()
                .userId(orderRequest.getUserId())
                .description(orderRequest.getDescription())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .status(OrderStatus.WAITING.getCode())
                .createdAt(System.currentTimeMillis())
                .phoneNumber(orderRequest.getPhoneNumber())
                .customerName(orderRequest.getCustomerName())
                .ownerId(orderRequest.getOwnerId())
                .treeId(orderRequest.getTreeId())
                .build();
        OrderList order = repository.save(orderList);
        BaseResponse<OrderDTO> response = new BaseResponse<>();
        response.setData(mapper.convertDto(order));
        return response;
    }
    
    @GetMapping("/{owner_id}")
    public BaseResponse getListOrderByOwnerId(
            @PathVariable(value = "owner_id") Long ownerId
    ) {
        BaseResponse<List<OrderDTO>> baseResponse = new BaseResponse();
        List<OrderList> orderLists = repository.findAllByOwnerId(ownerId);
        List<OrderDTO> orderDTOList = mapper.convertToListDTO(orderLists);
        baseResponse.setData(orderDTOList);
        return baseResponse;
    }
    
    @PutMapping("/accept/{order_id}")
    public BaseResponse acceptOrder(
            @PathVariable(value = "order_id") Long orderId
    ){
        BaseResponse<OrderDTO> response = new BaseResponse<>();
        OrderList orderList = repository.getById(orderId);
        orderList.setStatus(OrderStatus.ACCEPTED.getCode());
        OrderList order = repository.saveAndFlush(orderList);
        response.setData(mapper.convertDto(order));
        return response;
    }
    
    @PutMapping("/reject/{order_id}")
    public BaseResponse rejectOrder(
            @PathVariable(value = "order_id") Long orderId
    ){
        BaseResponse<OrderDTO> response = new BaseResponse<>();
        OrderList orderList = repository.getById(orderId);
        orderList.setStatus(OrderStatus.REJECTED.getCode());
        OrderList order = repository.saveAndFlush(orderList);
        response.setData(mapper.convertDto(order));
        return response;
    }
}

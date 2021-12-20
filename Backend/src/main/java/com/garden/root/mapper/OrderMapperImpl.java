package com.garden.root.mapper;

import com.garden.root.dto.OrderDTO;
import com.garden.root.dto.TreeDTO;
import com.garden.root.entity.OrderList;
import com.garden.root.entity.Tree;
import com.garden.root.services.TreeServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper{
    
    private final TreeServices treeServices;
    
    public OrderMapperImpl(TreeServices treeServices) {
        this.treeServices = treeServices;
    }
    
    @Override
    public OrderDTO convertDto(OrderList order) {
        TreeDTO tree = treeServices.getTreeById(order.getTreeId()).getData();
        return OrderDTO.builder()
                .customerName(order.getCustomerName())
                .quantity(order.getQuantity())
                .treeDTO(tree)
                .price(order.getPrice())
                .build();
    }
    
    @Override
    public List<OrderDTO> convertToListDTO(List<OrderList> orderLists) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderLists.forEach(orderList -> {
            orderDTOList.add(convertDto(orderList));
        });
        return orderDTOList;
    }
}

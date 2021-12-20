package com.garden.root.mapper;

import com.garden.root.dto.OrderDTO;
import com.garden.root.entity.OrderList;

import java.util.List;

public interface OrderMapper {
    
    OrderDTO convertDto(OrderList order);
    
    List<OrderDTO> convertToListDTO(List<OrderList> orderLists);
}

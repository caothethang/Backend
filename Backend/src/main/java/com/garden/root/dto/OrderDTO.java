package com.garden.root.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    
    @JsonProperty("user_name")
    private String userName;
    
    @JsonProperty("customer_name")
    private String customerName;
    
    private Double price;
    
    private Integer quantity;
    
    private TreeDTO treeDTO;
}

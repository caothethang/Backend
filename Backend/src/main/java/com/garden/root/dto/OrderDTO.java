package com.garden.root.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    
    @JsonProperty("order_id")
    private Long orderId;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("user_name")
    private String userName;
    
    @JsonProperty("customer_name")
    private String customerName;
    
    private Double price;
    
    private Integer quantity;
    
    private TreeDTO treeDTO;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    private String description;
    
    @JsonProperty("user_id")
    private Long userId;
}

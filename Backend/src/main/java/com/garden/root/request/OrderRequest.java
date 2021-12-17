package com.garden.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequest {
    
    @JsonProperty("customer_name")
    private String customerName;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    private Integer quantity;
    
    private Double price;
    
    @JsonProperty("owner_id")
    private Long ownerId;
    
    private String description;
    
    @JsonProperty("tree_id")
    private Long treeId;
    
}

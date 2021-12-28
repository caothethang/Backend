package com.garden.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class OrderRequest {
    
    @JsonProperty("user_id")
    @Nullable
    private Long userId;
    
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

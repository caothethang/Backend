package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddToCartRequest {
    
    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("product_id")
    private Long productId;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("price")
    private Long price;
    
    private String size;
    
    private String color;

    
}

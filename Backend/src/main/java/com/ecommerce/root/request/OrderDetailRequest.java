package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDetailRequest {
    
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("total_price")
    private Long totalPrice;
    @JsonProperty("size")
    private String size;
    @JsonProperty("gender")
    private Integer gender;
    private Long price;
    private String color;
}

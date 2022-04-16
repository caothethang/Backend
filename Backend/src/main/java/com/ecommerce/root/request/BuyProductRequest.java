package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BuyProductRequest {
    
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("total_price")
    private Long totalPrice;
    
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("received_address")
    private String receivedAddress;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
}

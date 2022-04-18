package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderHistoryResponse {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_image")
    private String productImage;
    private Long quantity;
    private String size;
    private Integer gender;
    @JsonProperty("received_address")
    private String receivedAddress;
    @JsonProperty("total_price")
    private Long totalPrice;
}

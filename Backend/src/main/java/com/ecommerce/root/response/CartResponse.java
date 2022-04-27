package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

    @JsonProperty("id")
    private Long cartItemId;

    @JsonProperty("product_image")
    private String productImage;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Long price;

}

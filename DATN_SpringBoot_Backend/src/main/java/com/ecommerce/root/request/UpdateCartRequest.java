package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateCartRequest {
    @JsonProperty("cart_item_id")
    private Long cartItemId;
    @JsonProperty("quantity")
    private Integer quantity;
}

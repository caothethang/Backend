package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailListResponse {

    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("size")
    private String size;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("color")
    private String color;
}

package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    @JsonProperty("order_id")
    private Long id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("price")
    private Long price;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private Integer status;
    @JsonProperty("created_at")
    private Long createdAt;
}

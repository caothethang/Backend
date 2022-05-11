package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    @JsonProperty("order_id")
    private Long id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("price")
    private Long price;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private Integer status;
    @JsonProperty("created_at")
    private Long createdAt;
    
    @JsonProperty("list_order_product")
    private List<OrderDetailListResponse> orderDetailListResponseList;
}

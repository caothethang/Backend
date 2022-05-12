package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BuyProductRequest {
    
    @JsonProperty("user_name")
    private String userName;
    
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("total_price")
    private Long totalPrice;
    
    @JsonProperty("list_order_details")
    private List<OrderDetailRequest> orderDetailRequests;
}

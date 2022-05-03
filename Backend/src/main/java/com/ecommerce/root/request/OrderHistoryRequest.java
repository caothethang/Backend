package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderHistoryRequest {

    @JsonProperty("user_name")
    private String userName;
}

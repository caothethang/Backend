package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    
    @JsonProperty("user_id")
    private Long userId;
    
    @JsonProperty("full_name")
    private String fullName;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
}

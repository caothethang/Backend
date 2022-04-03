package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    
    @JsonProperty("access_token")
    private String accessToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    
}

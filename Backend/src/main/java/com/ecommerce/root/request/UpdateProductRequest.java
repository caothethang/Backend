package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UpdateProductRequest {
    
    private String name;
    
    private String description;
    
    private String img;
    
    private Long price;
    
    private Long quantity;
    
    @JsonProperty("category_id")
    private Long categoryId;
}

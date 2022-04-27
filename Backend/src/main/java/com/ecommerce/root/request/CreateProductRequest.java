package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class CreateProductRequest {
    
    private String name;
    
    private String description;
    
    private String img;
    
    private Long price;
    
    private Long quantity;
    
    @JsonProperty("category_id")
    private Long categoryId;
    
    @JsonProperty("has_free_shipping")
    private Boolean hasFreeShipping;
    
    private List<String> size;
    
    private String color;
    
}

package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class UpdateProductRequest {
    
    private Long id;
    
    private String name;
    
    private String description;
    
    private String img;
    
    private Long price;
    
    private Long quantity;
    
    @JsonProperty("category_id")
    private Long categoryId;
    
    private List<String> size;
    
    private List<String> color;
    
    @JsonProperty("sale_percentage")
    private Integer salePercentage;
}

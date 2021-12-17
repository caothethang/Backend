package com.garden.root.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    
    @JsonProperty("created_at")
    private Long createdAt;
    
    @JsonProperty("image_uri")
    private String imageUri;
    
    @JsonProperty("category_id")
    private Long categoryId;
    
    @JsonProperty("owner_id")
    private Long ownerId;
}

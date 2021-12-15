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
    private Long createdAt;
    private String imageUri;
    
    @JsonProperty("category_id")
    private Long categoryId;
}

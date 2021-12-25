package com.garden.root.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeRequest {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long categoryId;
    private String imageUri;
    private Long userId;
}

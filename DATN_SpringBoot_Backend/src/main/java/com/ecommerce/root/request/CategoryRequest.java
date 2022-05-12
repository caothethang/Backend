package com.ecommerce.root.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryRequest {
    private Long id;
    @JsonProperty("category_name")
    private String categoryName;
}

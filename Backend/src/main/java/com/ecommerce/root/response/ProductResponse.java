package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long quantity;
    @JsonProperty("category_name")
    private String categoryName;
}

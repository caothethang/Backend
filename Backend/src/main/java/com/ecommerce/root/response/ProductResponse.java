package com.ecommerce.root.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Double price;
    private Long quantity;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("sale_voucher_code")
    private String saleVoucher;
}

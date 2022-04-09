package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.Product;
import com.ecommerce.root.response.ProductResponse;

public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}

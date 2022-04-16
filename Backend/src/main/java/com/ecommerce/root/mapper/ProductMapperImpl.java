package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.Product;
import com.ecommerce.root.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapperImpl implements ProductMapper{
    
    @Override
    public ProductResponse toProductResponse(Product product) {
        if (Objects.isNull(product)){
            return null;
        }
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryName(product.getCategory().getName())
                .build();
    }
}

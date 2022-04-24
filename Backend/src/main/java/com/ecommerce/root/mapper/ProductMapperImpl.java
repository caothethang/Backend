package com.ecommerce.root.mapper;

import com.ecommerce.root.entity.Product;
import com.ecommerce.root.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper{
    
    @Override
    public ProductResponse toProductResponse(Product product) {
        if (Objects.isNull(product)){
            return null;
        }
        List<String> size = null;
        if (Objects.nonNull(product.getSize())){
            size = Arrays.stream(product.getSize().split(",")).collect(Collectors.toList());
        }
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .size(size)
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .hasFreeShipping(product.isHasFreeShipping())
                .rate(product.getRate())
                .build();
    }
}

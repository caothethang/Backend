package com.ecommerce.root.services;

import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.ProductResponse;

import java.util.List;

public interface ProductService {
    
    BaseResponse createProduct(CreateProductRequest request);
    
    ProductResponse updateProduct(UpdateProductRequest request);
    
    void deleteProduct(Long id);
    
    ProductResponse getProductDetail(Long id);
    
    List<ProductResponse> getAllProduct(String category, Long minPrice, Long maxPrice, String name,Integer sort, Integer pageIndex);
}

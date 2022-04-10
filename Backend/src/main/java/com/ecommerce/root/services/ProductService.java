package com.ecommerce.root.services;

import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.ProductResponse;

import java.util.List;

public interface ProductService {
    
    BaseResponse createProduct(CreateProductRequest request);
    
    List<ProductResponse> getAllProduct();
    
    ProductResponse updateProduct(Long productId, UpdateProductRequest request);
    
    void deleteProduct(Long id);
}

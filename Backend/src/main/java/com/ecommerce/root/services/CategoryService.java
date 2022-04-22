package com.ecommerce.root.services;

import com.ecommerce.root.request.CategoryRequest;
import com.ecommerce.root.response.BaseResponse;

public interface CategoryService {
    BaseResponse createCategory(CategoryRequest request);
    
    BaseResponse editCategory(Long id,CategoryRequest request);
}

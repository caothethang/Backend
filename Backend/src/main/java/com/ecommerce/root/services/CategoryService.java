package com.ecommerce.root.services;

import com.ecommerce.root.entity.Category;
import com.ecommerce.root.request.CategoryRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.CategoryResponse;

public interface CategoryService {
    BaseResponse createCategory(CategoryRequest request);
    
    BaseResponse editCategory(CategoryRequest request);

    CategoryResponse getCategoryById(Long id);
}

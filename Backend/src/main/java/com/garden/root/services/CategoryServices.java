package com.garden.root.services;

import com.garden.root.entity.Category;
import com.garden.root.response.BaseResponse;

public interface CategoryServices {
    
    BaseResponse getAllCategory();
    
    BaseResponse<Category> getCategoryById(Long id);
    
}

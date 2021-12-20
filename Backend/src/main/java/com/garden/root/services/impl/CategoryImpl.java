package com.garden.root.services.impl;

import com.garden.root.entity.Category;
import com.garden.root.repositories.CategoryRepository;
import com.garden.root.response.BaseResponse;
import com.garden.root.services.CategoryServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryServices {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public BaseResponse getAllCategory() {
        BaseResponse response = new BaseResponse();
        List<Category> categories = categoryRepository.findAll();
        response.setData(categories);
        return response;
    }
    
    @Override
    public BaseResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).get();
        BaseResponse response = new BaseResponse();
        response.setData(category);
        return response;
    }
}

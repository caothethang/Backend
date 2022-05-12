package com.ecommerce.root.services.impl;

import com.ecommerce.root.entity.Category;
import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.request.CategoryRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.CategoryResponse;
import com.ecommerce.root.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Override
    public BaseResponse createCategory(CategoryRequest request) {
        BaseResponse response = BaseResponse.builder().build();
        try {
            String categoryName = request.getCategoryName();
            Category category = Category.builder()
                    .name(categoryName)
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            categoryRepository.save(category);
            response.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            response.setFailed(500,"Fail Create Category");
        }
        return response;
    
    }
    
    @Override
    public BaseResponse editCategory(CategoryRequest request) {
        BaseResponse response = BaseResponse.builder().build();
        Category category = categoryRepository.findById(request.getId()).get();
        if (category == null){
            response.setFailed(500,"Fail Edit Category");
            return response;
        }
        category.setName(request.getCategoryName());
        categoryRepository.save(category);
        response.setSuccess();
        return response;
        
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            Category entity = category.get();
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
            return categoryResponse;
        }
        return null;
    }
}

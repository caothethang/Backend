package com.ecommerce.root.controller;

import com.ecommerce.root.entity.Category;
import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.response.CategoryResponse;
import com.ecommerce.root.services.CategoryService;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {
    
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    
    @GetMapping("/category")
    public List<CategoryResponse> getListCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categories.forEach(category -> {
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            categoryResponses.add(categoryResponse);
        });
        return categoryResponses;
    }

    @GetMapping("/category/detail")
    public CategoryResponse getCategory(
            @RequestParam(value = "id") Long id
    ){
        return categoryService.getCategoryById(id);
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getListProduct(){
        return null;
    }
    
    @GetMapping("/product/detail")
    public ResponseEntity<?> getProductDetails(
            @RequestParam(value = "id") Long productId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetail(productId));
    }
}

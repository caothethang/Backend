package com.ecommerce.root.controller;

import com.ecommerce.root.entity.Category;
import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.response.CategoryResponse;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {
    
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    
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
    
    @GetMapping("/product")
    public ResponseEntity<?> getListProduct(){
        return null;
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductDetails(
            @PathVariable(value = "id") Long productId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetail(productId));
    }
}

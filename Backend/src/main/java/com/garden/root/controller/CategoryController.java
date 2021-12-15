package com.garden.root.controller;

import com.garden.root.response.BaseResponse;
import com.garden.root.services.CategoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {
    
    private final CategoryServices categoryServices;
    
    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }
    
    @GetMapping
    public ResponseEntity getCategory(){
        BaseResponse response = categoryServices.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}

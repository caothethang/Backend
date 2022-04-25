package com.ecommerce.root.controller.secure.admin;

import com.ecommerce.root.repositories.CategoryRepository;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.request.CategoryRequest;
import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.services.AdminService;
import com.ecommerce.root.services.CategoryService;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final AdminService adminService;
    private final CategoryRepository categoryRepository;
    
    @GetMapping("/users")
    public ResponseEntity<?> getListUser() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getListUser());
    }
    
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "min_price", required = false) Long minPrice,
            @RequestParam(value = "max_price", required = false) Long maxPrice,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam(value = "page", required = false) Integer pageIndex
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(category, minPrice, maxPrice, name, sort, pageIndex));
    }
    
    @PostMapping("/product/update")
    public ResponseEntity<?> updateProduct(
            @RequestBody UpdateProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(request));
    }
    
    @PostMapping("/product/delete")
    public ResponseEntity<?> deleteProduct(
            @RequestParam(value = "id") Long id
    ) {
        productService.deleteProduct(id);
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(BaseResponse.builder()
                    .status(200)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                    .status(500)
                    .build());
        }
    }
    
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }
    
    @PostMapping("/category/edit")
    public ResponseEntity<?> editCategory(
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.editCategory(request));
    }
    
    @PostMapping("/category/delete")
    public ResponseEntity<?> deleteCategory(
            @RequestParam("id") Long id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok(BaseResponse.builder()
                    .status(200)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.builder()
                    .status(500)
                    .build());
        }
    }
    
}

package com.ecommerce.root.controller.secure.admin;

import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.request.CategoryRequest;
import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
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
    
    @GetMapping("/users")
    public ResponseEntity<?> getListUser(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getListUser());
    }
    
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "category",required = false)String category,
            @RequestParam(value = "min_price",required = false) Long minPrice,
            @RequestParam(value = "max_price",required = false) Long maxPrice,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sort",required = false) Integer sort,
            @RequestParam(value = "page",required = false) Integer pageIndex
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(category,minPrice,maxPrice,name,sort,pageIndex));
    }
    
    @PutMapping("/product/update")
    public ResponseEntity<?> updateProduct(
            @RequestParam(value = "id") Long productId,@RequestBody UpdateProductRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId,request));
    }
    
    @DeleteMapping("/product/delete")
    public ResponseEntity<?> deleteProduct(
            @RequestParam(value = "id") Long id
    ){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
    }
    
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }
    
    @PutMapping("/category/{id}")
    public ResponseEntity<?> editCategory(
            @PathVariable(name = "id") Long id
            ,@Valid @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.editCategory(id,request));
    }
    
}

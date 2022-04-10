package com.ecommerce.root.controller.secure.admin;

import com.ecommerce.root.request.CreateProductRequest;
import com.ecommerce.root.request.UpdateProductRequest;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    
    private final ProductService productService;
    
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable(value = "id") Long productId,@RequestBody UpdateProductRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId,request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable(value = "id") Long id
    ){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
    }
}

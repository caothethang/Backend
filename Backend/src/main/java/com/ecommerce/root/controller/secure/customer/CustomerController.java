package com.ecommerce.root.controller.secure.customer;

import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.services.OrderService;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    
    private final OrderService orderService;
    private final ProductService productService;
    
//    @PostMapping("/add-to-cart")
//    public ResponseEntity<?> addToCart(){
//
//    }
    
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProductRequest buyProductRequest){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.buyProduct(buyProductRequest));
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "category",required = false)String category,
            @RequestParam(value = "min_price",required = false) Long minPrice,
            @RequestParam(value = "max_price",required = false) Long maxPrice,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sort") Integer sort,
            @RequestParam(value = "page") Integer pageIndex
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(category,minPrice,maxPrice,name,sort,pageIndex));
    }
    
    
    
    // xem lịch sử mua hàng
    @GetMapping("/order/history")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrderHistory(HttpServletRequest servletRequest,
            @RequestBody OrderHistoryRequest orderHistoryRequest
    ){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderHistory(orderHistoryRequest));
    }
}

package com.ecommerce.root.controller.secure.customer;

import com.ecommerce.root.request.AddToCartRequest;
import com.ecommerce.root.request.BuyProductRequest;
import com.ecommerce.root.request.OrderHistoryRequest;
import com.ecommerce.root.request.UpdateCartRequest;
import com.ecommerce.root.services.CartService;
import com.ecommerce.root.services.OrderService;
import com.ecommerce.root.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    
    private final OrderService orderService;
    private final ProductService productService;
    private final CartService cartService;
    
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(
            @RequestBody AddToCartRequest addToCartRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(addToCartRequest));
    }
    
    @GetMapping("/cart")
    public ResponseEntity<?> getCart(
            @RequestParam(value = "id") Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart(userId));
    }
    
    @PostMapping("/cart/update")
    public ResponseEntity<?> updateCart(
            @RequestBody List<UpdateCartRequest> updateCartRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.updateCart(updateCartRequest));
    }
    
    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeCartItem(
            @RequestParam("id") Long cartItemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.removeCartItem(cartItemId));
    }
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProductRequest buyProductRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.buyProduct(buyProductRequest));
    }
    
    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "min_price", required = false) Long minPrice,
            @RequestParam(value = "max_price", required = false) Long maxPrice,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sort") Integer sort,
            @RequestParam(value = "page") Integer pageIndex
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(category, minPrice, maxPrice, name, sort, pageIndex));
    }
    
    // xem lịch sử mua hàng
    @PostMapping("/order/history")
    public ResponseEntity<?> getOrderHistory(
            @RequestBody OrderHistoryRequest orderHistoryRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderHistory(orderHistoryRequest));
    }
    
    @PostMapping("/order/delete")
    public ResponseEntity<?> deleteOrder(
            @RequestParam("id") Long orderId
    ) {
     return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(orderId));
    }
    
    @GetMapping("/order/detail")
    public ResponseEntity<?> getOrderDetail(
            @RequestParam("id") Long orderId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDetail(orderId));
    }
    
}

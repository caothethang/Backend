package com.ecommerce.root.controller.secure.customer;

import com.ecommerce.root.request.BuyProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody BuyProductRequest buyProductRequest){
        return null;
    }
}

package com.ecommerce.root.services.impl;

import com.ecommerce.root.entity.Cart;
import com.ecommerce.root.entity.CartItem;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.entity.User;
import com.ecommerce.root.repositories.CartRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.request.AddToCartRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartImpl implements CartService {
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    
    @Override
    public BaseResponse addToCart(AddToCartRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        if (!optionalProduct.isPresent()){
            return BaseResponse.builder()
                    .status(500)
                    .data("Không tìm thấy product")
                    .build();
        }
        Cart cart = cartRepository.findByUserId(request.getUserId());
        if (Objects.nonNull(cart)){
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .productName(optionalProduct.get().getName())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
        }
        
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (!optionalUser.isPresent()) {
            return BaseResponse.builder()
                    .status(503)
                    .data("Không có user")
                    .build();
        }
        return null;
        
    }
}

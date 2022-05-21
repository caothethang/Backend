package com.ecommerce.root.services.impl;

import com.ecommerce.root.entity.Cart;
import com.ecommerce.root.entity.CartItem;
import com.ecommerce.root.entity.Product;
import com.ecommerce.root.entity.User;
import com.ecommerce.root.mapper.CartMapper;
import com.ecommerce.root.repositories.CartItemRepository;
import com.ecommerce.root.repositories.CartRepository;
import com.ecommerce.root.repositories.ProductRepository;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.request.AddToCartRequest;
import com.ecommerce.root.request.UpdateCartRequest;
import com.ecommerce.root.response.BaseResponse;
import com.ecommerce.root.response.CartResponse;
import com.ecommerce.root.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartImpl implements CartService {
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    
    @Override
    public BaseResponse addToCart(AddToCartRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (!optionalUser.isPresent()) {
            return BaseResponse.builder()
                    .status(503)
                    .data("Không có user")
                    .build();
        }
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        if (!optionalProduct.isPresent()){
            return BaseResponse.builder()
                    .status(500)
                    .data("Không tìm thấy product")
                    .build();
        }
        Cart cart = cartRepository.findByUserId(request.getUserId());

        Product product = optionalProduct.get();
        if (Objects.nonNull(cart)){
            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem e : cartItems){
                if (e.getProductId() == request.getProductId()){
                    return BaseResponse.builder()
                            .status(501)
                            .data("Đã tồn tại trong giỏ hàng")
                            .build();
                }
            }

            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .price(request.getPrice())
                    .categoryName(product.getCategory().getName())
                    .quantity(request.getQuantity())
                    .productId(product.getId())
                    .productName(product.getName())
                    .productQuantity(product.getQuantity())
                    .image(product.getImage())
                    .size(request.getSize())
                    .color(request.getColor())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            cartItemRepository.save(cartItem);
        }else {
            cart = Cart.builder()
                    .user(optionalUser.get())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            cart = cartRepository.save(cart);

            CartItem cartItem = CartItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productQuantity(product.getQuantity())
                    .quantity(request.getQuantity())
                    .cart(cart)
                    .size(request.getSize())
                    .color(request.getColor())
                    .image(product.getImage())
                    .price(request.getPrice())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            cartItemRepository.save(cartItem);
        }
        return BaseResponse.builder()
                .status(200)
                .data("Thêm vào giỏ hàng thành công")
                .build();
        
    }

    @Override
    public Object getCart(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        List<CartResponse> listCartResponse = new ArrayList<>();
        if (!optionalUser.isPresent()) {
            return BaseResponse.builder()
                    .status(503)
                    .data("Không có user")
                    .build();
        }else {
            Cart cart = cartRepository.findByUserId(userId);
            if (Objects.nonNull(cart)){
                List<CartItem> cartItems = cart.getCartItems();
                for (CartItem e : cartItems){
                    listCartResponse.add(cartMapper.toResponse(e));
                }
            }
        }
        return BaseResponse.builder()
                .data(listCartResponse)
                .status(200)
                .build();
    }

    @Override
    public Object removeCartItem(Long cartItemId) {
        try {
            cartItemRepository.deleteById(cartItemId);
            return BaseResponse.builder()
                    .status(200)
                    .data("Xóa thành công")
                    .build();
        } catch (Exception e) {
            return BaseResponse.builder()
                    .status(500)
                    .data("Xóa không thành công")
                    .build();
        }
    }

    @Override
    public Object updateCart(List<UpdateCartRequest> updateCartRequest) {
        List<CartItem> cartItems = new ArrayList<>();
        updateCartRequest.forEach(request ->{
            Long cartItemId = request.getCartItemId();
            CartItem cartItem = cartItemRepository.getById(cartItemId);
            cartItem.setQuantity(request.getQuantity());
            cartItems.add(cartItem);
        });
        cartItemRepository.saveAll(cartItems);
        return BaseResponse.builder()
                .data("Update thành công")
                .status(200)
                .build();
    }
}

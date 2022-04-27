package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.Cart;
import com.ecommerce.root.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}

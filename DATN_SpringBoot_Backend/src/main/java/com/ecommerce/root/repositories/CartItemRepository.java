package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.Cart;
import com.ecommerce.root.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    
    @Query(value = "delete from cart_item where id = :id",nativeQuery = true)
    @Transactional
    @Modifying
    void deleteById(Long id);
}

package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.Cart;
import com.ecommerce.root.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    
    @Query(value = "select b1.* from cart b1 where b1.user_id = :user_id",nativeQuery = true)
    Cart findByUserId(@Param("user_id") Long userId);
    
    @Modifying
    @Transactional
    void deleteByUser(User user);
}

package com.garden.root.repositories;

import com.garden.root.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Cart,Long> {
}

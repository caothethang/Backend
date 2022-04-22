package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    
}

package com.garden.root.repositories;

import com.garden.root.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderList,Long> {
    
    List<OrderList> findAllByOwnerId(String ownerId);
}

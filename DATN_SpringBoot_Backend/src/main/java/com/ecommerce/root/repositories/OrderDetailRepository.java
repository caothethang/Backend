package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.OrderDetails;
import com.ecommerce.root.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {

    List<OrderDetails> findAllByOrders(Orders orders);
}

package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.CustomerInfo;
import com.ecommerce.root.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {


    List<Orders> findAllByCustomerInfo(CustomerInfo customerInfo);
    
    List<Orders> findAllByCreatedAtGreaterThanAndCreatedAtLessThanAndStatus(Timestamp startTime, Timestamp endTime,Integer status);
}

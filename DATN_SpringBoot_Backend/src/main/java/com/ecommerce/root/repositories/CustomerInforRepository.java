package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInforRepository extends JpaRepository<CustomerInfo,Long> {
    
    List<CustomerInfo> findAllByEmail(String email);

    List<CustomerInfo> findAllByUsername(String username);
}

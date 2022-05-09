package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepo extends JpaRepository<CustomerProfile,Long> {
    
    CustomerProfile findByUserId(Long userId);
}

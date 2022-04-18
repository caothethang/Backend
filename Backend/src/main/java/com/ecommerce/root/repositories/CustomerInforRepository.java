package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInforRepository extends JpaRepository<CustomerInfo,Long> {
}

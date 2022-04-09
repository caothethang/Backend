package com.ecommerce.root.repositories;

import com.ecommerce.root.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    
    Optional<Sale> findById(Long saleId);
}

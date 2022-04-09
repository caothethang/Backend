package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_sale")
@SuperBuilder(toBuilder = true)
public class Sale extends BaseEntity{

    @Column(name = "voucher_code")
    private String voucherCode;
    
    @Column(name = "percentage")
    private Integer percentage;
    
    @Column(name = "starting_at")
    private Timestamp startingAt;
    
    @Column(name = "ending_at")
    private Timestamp endingAt;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToMany(mappedBy = "sale",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> products;
    
}

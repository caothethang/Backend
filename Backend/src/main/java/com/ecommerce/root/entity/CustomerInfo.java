package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_customer_infor")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Data
@SuperBuilder(toBuilder = true)
public class CustomerInfo extends BaseEntity {
    
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(mappedBy = "customerInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Orders> orders;
}

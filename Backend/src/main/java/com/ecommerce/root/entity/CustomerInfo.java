package com.ecommerce.root.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
@SuperBuilder(toBuilder = true)
public class CustomerInfo extends BaseEntity{
    
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone")
    private String phone;
    
    @OneToMany(mappedBy = "customerInfo",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Orders> orders;
}

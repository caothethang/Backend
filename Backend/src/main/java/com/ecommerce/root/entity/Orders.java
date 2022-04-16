package com.ecommerce.root.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_orders")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SuperBuilder(toBuilder = true)
public class Orders extends BaseEntity{

    @JsonProperty("received_address")
    private String receivedAddress;
    @JsonProperty("status")
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name = "customer_infor_id")
    @ToString.Exclude
    private CustomerInfo customerInfo;

}

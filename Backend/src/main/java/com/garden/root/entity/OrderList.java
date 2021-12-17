package com.garden.root.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class OrderList extends BaseEntity{
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "tree_id")
    private Long treeId;
    
    private Integer quantity;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    private Double price;
    
    @Column(name = "owner_id")
    private Long ownerId;
    
    private Integer status;
}

package com.ecommerce.root.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_order_detail")
@DynamicUpdate
public class OrderDetails extends BaseEntity{
    
    private Long quantity;

}

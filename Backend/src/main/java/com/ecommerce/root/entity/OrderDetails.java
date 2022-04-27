package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tbl_order_detail")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class OrderDetails extends BaseEntity{
    
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "size")
    private String size;
    
    @Column(name = "price")
    private Long price;

    @Column(name = "color")
    private String color;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

}

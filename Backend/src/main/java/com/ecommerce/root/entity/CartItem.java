package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@SuperBuilder(toBuilder = true)
public class CartItem extends BaseEntity{
    
    @ManyToOne
    private Cart cart;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "price")
    private Long price;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}

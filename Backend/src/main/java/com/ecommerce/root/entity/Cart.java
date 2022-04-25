package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Cart extends BaseEntity{

    @ManyToOne
    private User user;
    
    @Column(name = "price")
    private Long price;
}

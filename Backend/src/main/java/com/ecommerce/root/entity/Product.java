package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
public class Product extends BaseEntity{

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    private Long quantity;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "price")
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @ToString.Exclude
    private Sale sale;
}

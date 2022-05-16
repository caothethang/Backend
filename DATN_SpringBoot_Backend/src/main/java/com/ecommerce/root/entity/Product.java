package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

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
    
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "quantity")
    private Long quantity;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "price")
    private Long price;
    
    @Column(name = "size")
    private String size;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "has_free_shipping")
    private boolean hasFreeShipping;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    
    @Column(name = "rating")
    private Integer rate;
    
    @Column(name = "source",columnDefinition = "TEXT")
    private String source;
    
    @Column(name = "sale_percentage")
    private Integer salePercentage;
}

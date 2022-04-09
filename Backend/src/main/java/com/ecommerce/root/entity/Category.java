package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Category extends BaseEntity{
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Product> products;
    
}

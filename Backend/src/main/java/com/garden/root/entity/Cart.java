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
public class Cart extends BaseEntity{
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "description")
    private String description;
    
    private Long treeId;
    
    private Integer quantity;
    
    private Double price;
}

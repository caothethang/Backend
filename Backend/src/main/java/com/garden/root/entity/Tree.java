package com.garden.root.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public class Tree extends BaseEntity{
    
    @NotBlank
    @Column(name = "name")
    private String name;
    
    @NotEmpty
    @Column(name = "price")
    private Double price;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "images_uri")
    private String imageUri;
    
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @JsonIgnore
    private Category category;
    
}

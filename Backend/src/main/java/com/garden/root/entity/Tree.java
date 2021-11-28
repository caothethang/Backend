package com.garden.root.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Tree extends BaseEntity{
    
    @NotBlank
    private String name;
    
    @NotEmpty
    @Column(name = "price")
    private Double price;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "tree_type_id",referencedColumnName = "id")
    @JsonIgnore
    private TreeType treeType;
    
}

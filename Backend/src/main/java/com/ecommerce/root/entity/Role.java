package com.ecommerce.root.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    
    @Column(name = "name")
    private String name;
    
}

package com.garden.root.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tree_type")
public class TreeType extends BaseEntity{
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "treeType",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tree> trees;
}

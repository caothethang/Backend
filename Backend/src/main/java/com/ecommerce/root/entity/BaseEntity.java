package com.ecommerce.root.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    protected Long id;
    
    @Column(name = "created_at")
    protected Timestamp createdAt;
    
    @Column(name = "updated_at")
    protected Timestamp updatedAt;
    
    @Column(name = "is_deleted")
    protected Boolean isDeleted;

}

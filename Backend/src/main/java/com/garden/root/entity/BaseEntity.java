package com.garden.root.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @CreatedDate
    @JsonProperty("created_at")
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @CreatedBy
    @JsonProperty("created_by")
    @Column(name = "created_by")
    private LocalDateTime createdBy;
    
    @LastModifiedBy
    @JsonProperty("updated_by")
    @Column(name = "updated_by")
    private LocalDateTime updatedBy;
}

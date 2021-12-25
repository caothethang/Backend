package com.garden.root.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;
    
    @CreatedDate
    @JsonProperty("created_at")
    protected Long createdAt;
    
    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    protected Long updatedAt;
    
    @JsonProperty("created_by")
    @Column(name = "created_by")
    protected String createdBy;
    
    @JsonProperty("updated_by")
    @Column(name = "updated_by")
    protected String updatedBy;
}

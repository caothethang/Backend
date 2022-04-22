package com.ecommerce.root.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_orders")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Data
@SuperBuilder(toBuilder = true)
public class Orders extends BaseEntity{

    @JsonProperty("received_address")
    private String receivedAddress;
    @JsonProperty("status")
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name = "customer_infor_id")
    @ToString.Exclude
    private CustomerInfo customerInfo;
    
    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails;

}

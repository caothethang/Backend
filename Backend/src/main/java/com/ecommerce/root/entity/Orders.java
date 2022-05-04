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

    @Column(name = "user_name")
    private String userName;
    
    @Column(name ="received_address")
    private String receivedAddress;
    @Column(name ="status")
    private Integer status;

    @Column(name = "total_price")
    private Long totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "customer_infor_id")
    @ToString.Exclude
    private CustomerInfo customerInfo;
    
    @OneToMany(mappedBy = "orders",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderDetails> orderDetails;

}

package com.retra.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long buyerId;
    private Long sellerId;
    private Long productId;

    private int quantity;
    @Setter
    private OrderStatus orderStatus;
    private Date orderDate;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Shipping shipping;

    private Double price;

}

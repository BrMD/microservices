package com.order.order.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    public Products(String productId, String name, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }
}

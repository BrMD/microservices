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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String cartId;

    @Column(nullable = false)
    private Double orderValue;

    public Order(UUID userId, String cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }
}

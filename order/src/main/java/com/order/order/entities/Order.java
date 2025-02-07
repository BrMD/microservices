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
    private String userId;

    @Column(nullable = false, unique = true)
    private String cartId;

    @Column(nullable = false)
    private Float orderValue;

    @Column(nullable = false)
    private Integer status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="productId")
    private Products products;

    public Order(String userId, String cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }



    public Order(UUID id, String userId, String cartId, Integer status,Float orderValue) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
        this.orderValue = orderValue;
        this.status = status;
    }
}

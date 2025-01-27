package com.service.stock.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String idProduct;

    @Column(nullable = false)
    private Integer quantity;

    public Stock(String idProduct, Integer quantity) {
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public void decrease(Integer decQuantity){
        quantity = quantity - decQuantity;
    }

    public void increase(Integer incQuantity){
        quantity = quantity + incQuantity;
    }
}

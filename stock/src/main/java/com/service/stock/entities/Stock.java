package com.service.stock.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Stock {
    @Id
    private String id;

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

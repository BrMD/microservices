package com.service.cart.hash;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    private String productId;
    private Integer quantity;

}

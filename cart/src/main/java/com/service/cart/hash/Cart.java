package com.service.cart.hash;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RedisHash
public class Cart {
    @Id
    private String id;

    @Indexed
    private String userId;

    private List<Product> products;

    public void addProduct(Product product){
        this.products.add(product);
    }
}

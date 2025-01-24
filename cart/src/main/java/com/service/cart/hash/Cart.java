package com.service.cart.hash;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RedisHash
public class Cart {
    @Id
    @Indexed
    private String id;

    @Indexed
    private String userId;

    private List<Product> products;


    public void addProduct(Product product){
        if(products.stream().anyMatch(prod -> prod.getProductId().equals(product.getProductId()))){
            List <Product> findedProd = products.stream().filter(prod -> prod.getProductId().equals(product.getProductId())).toList();
            products.remove(findedProd.getFirst());
        }
        if(product.getQuantity() > 0)products.add(product);
    }
}

package com.service.cart.repositories;

import com.service.cart.hash.Cart;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public interface CartRepository  extends CrudRepository<Cart, String> {
    Optional<Cart> findByUserId(String id);
}

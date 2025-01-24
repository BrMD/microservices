package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.exceptions.CartNotFoundException;
import com.service.cart.hash.Cart;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.DeleteCartUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteCartImpl implements DeleteCartUseCase {
    private final CartRepository cartRepository;

    public DeleteCartImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void delete(String id){
        Cart cartFinded = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
        cartRepository.deleteById(cartFinded.getId());
    }
}

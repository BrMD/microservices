package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.exceptions.CartNotFoundException;
import com.service.cart.hash.Cart;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.DeleteCartUseCase;

public class DeleteCartImpl implements DeleteCartUseCase {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    public DeleteCartImpl(CartMapper cartMapper, CartRepository cartRepository) {
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
    }

    @Override
    public void delete(String id){
        Cart cartFinded = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
        cartRepository.deleteById(cartFinded.getId());
    }
}

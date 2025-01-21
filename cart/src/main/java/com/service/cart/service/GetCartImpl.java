package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.dto.response.CartDto;
import com.service.cart.exceptions.CartNotFoundException;
import com.service.cart.hash.Cart;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.GetCartUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCartImpl implements GetCartUseCase {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public GetCartImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartDto getCart(String id){
        Cart cartFinded = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
        return cartMapper.toDto(cartFinded);
    }
}

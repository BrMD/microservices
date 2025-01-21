package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.dto.request.CartDtoRequest;
import com.service.cart.dto.response.CartDto;
import com.service.cart.dto.response.PutOrCreateDtoResponse;
import com.service.cart.hash.Cart;
import com.service.cart.hash.Product;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.PutOrCreateCartUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PutOrCreateImpl implements PutOrCreateCartUseCase {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public PutOrCreateImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }
    @Override
    public PutOrCreateDtoResponse putOrCreate(String userId, CartDtoRequest cartDtoRequest){
        PutOrCreateDtoResponse putOrCreateDtoResponse = new PutOrCreateDtoResponse();
        putOrCreateDtoResponse.setCode(200);
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() ->{
            putOrCreateDtoResponse.setCode(204);
            return create(userId);
        });
        cart.addProduct(cartDtoRequest.getProduct());
        cartRepository.save(cart);
        return new PutOrCreateDtoResponse();
    }

    public Cart create(String userId){
        Cart cartToBeSaved = new Cart();
        cartToBeSaved.setUserId(userId);
        cartToBeSaved.setProducts(new ArrayList<Product>());
        return cartRepository.save(cartToBeSaved);
    }
}

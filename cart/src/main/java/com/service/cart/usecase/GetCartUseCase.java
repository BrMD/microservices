package com.service.cart.usecase;

import com.service.cart.dto.response.CartDto;

public interface GetCartUseCase {
    public CartDto getCart(String id);
}

package com.service.cart.usecase;

import com.service.cart.dto.request.CartDtoRequest;
import com.service.cart.dto.response.PutOrCreateDtoResponse;

public interface PutOrCreateCartUseCase {
    public PutOrCreateDtoResponse putOrCreate(String userId, CartDtoRequest cartDtoRequest);
}

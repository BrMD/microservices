package com.service.stock.usecase;

import java.util.UUID;

public interface PutIncreaseUseCase {
    public void incQuantity(String id, Integer incQuantity);
}

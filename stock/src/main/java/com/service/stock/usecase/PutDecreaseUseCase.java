package com.service.stock.usecase;

import java.util.UUID;

public interface PutDecreaseUseCase {
    public void decQuantity(String id, Integer decQuantity);
}

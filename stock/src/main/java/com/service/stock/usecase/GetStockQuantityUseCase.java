package com.service.stock.usecase;

import com.service.stock.dto.response.StockDto;

public interface GetStockQuantityUseCase {
    public StockDto getQuantity(String id);
}

package com.service.stock.usecase;

import com.service.stock.dto.request.StockDtoRequest;
import com.service.stock.dto.response.StockDto;

public interface CreateStockUseCase {
    public StockDto create(StockDtoRequest stockDtoRequest);
}

package com.service.stock.service;

import com.service.stock.exceptions.StockNotFoundException;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.PutIncreaseUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PutIncreaseImpl implements PutIncreaseUseCase {
    private final StockRepository stockRepository;

    public PutIncreaseImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void incQuantity(String id, Integer incQuantity){
        var stockFound = stockRepository.findByIdProduct(id).orElseThrow(() -> new StockNotFoundException(id));
        stockFound.increase(incQuantity);
        stockRepository.save(stockFound);
    }
}

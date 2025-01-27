package com.service.stock.service;

import com.service.stock.exceptions.InsufficientStockException;
import com.service.stock.exceptions.StockNotFoundException;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.PutDecreaseUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PutDecreaseImpl implements PutDecreaseUseCase {
    private final StockRepository stockRepository;

    public PutDecreaseImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void decQuantity(String id, Integer decQuantity){
        var stockFound = stockRepository.findByIdProduct(id).orElseThrow(() -> new StockNotFoundException(id));
        if(stockFound.getQuantity() < decQuantity) throw new InsufficientStockException(id);
        stockFound.decrease(decQuantity);
        stockRepository.save(stockFound);
    }
}

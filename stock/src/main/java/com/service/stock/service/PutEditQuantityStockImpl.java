package com.service.stock.service;

import com.service.stock.exceptions.InsufficientStockException;
import com.service.stock.exceptions.StockNotFoundException;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.PutEditQuantityStockUseCase;
import org.springframework.stereotype.Service;

@Service
public class PutEditQuantityStockImpl implements PutEditQuantityStockUseCase {
    private final StockRepository stockRepository;

    public PutEditQuantityStockImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void editStock(String id, Integer editQuantity){
        var stockFound = stockRepository.findByIdProduct(id).orElseThrow(() -> new StockNotFoundException(id));
        stockFound.setQuantity(editQuantity);
        stockRepository.save(stockFound);
    }
}

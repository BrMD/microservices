package com.service.stock.service;

import com.service.stock.exceptions.StockNotFoundException;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.DeleteStockUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteStockImpl implements DeleteStockUseCase {
    private final StockRepository stockRepository;

    public DeleteStockImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void delete(String id){
        var stockFound = stockRepository.findByIdProduct(id).orElseThrow(() -> new StockNotFoundException(id));
        stockRepository.deleteById(stockFound.getId());
    }
}

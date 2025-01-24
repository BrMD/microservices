package com.service.stock.service;

import com.service.stock.dto.mapper.StockMapper;
import com.service.stock.dto.response.StockDto;
import com.service.stock.exceptions.StockNotFoundException;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.GetStockQuantityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetStockQuantityImpl implements GetStockQuantityUseCase {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public GetStockQuantityImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public StockDto getQuantity(String id){
        var stockFinded = stockRepository.findByIdProduct(id).orElseThrow(() -> new StockNotFoundException(id));
        return stockMapper.toDto(stockFinded);
    }
}

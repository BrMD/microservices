package com.service.stock.service;

import com.service.stock.dto.mapper.StockMapper;
import com.service.stock.dto.request.StockDtoRequest;
import com.service.stock.dto.response.StockDto;
import com.service.stock.exceptions.StockAlreadyExistsInTheDatabase;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.CreateStockUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateStockImpl implements CreateStockUseCase {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public CreateStockImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public StockDto create(StockDtoRequest stockDtoRequest){
        if(!stockRepository.existsByIdProduct(stockDtoRequest.getIdProduct())){
            throw new StockAlreadyExistsInTheDatabase(stockDtoRequest.getIdProduct());
        }
        return stockMapper.toDto(stockRepository.save(stockMapper.toEntity(stockDtoRequest)));
    }
}

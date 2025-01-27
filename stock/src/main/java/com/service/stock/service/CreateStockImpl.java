package com.service.stock.service;

import com.service.stock.dto.mapper.StockMapper;
import com.service.stock.dto.request.StockDtoRequest;
import com.service.stock.dto.response.StockDto;
import com.service.stock.exceptions.StockAlreadyExistsInTheDatabase;
import com.service.stock.repositories.StockRepository;
import com.service.stock.usecase.CreateStockUseCase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        if(Boolean.TRUE.equals(stockRepository.existsByIdProduct(stockDtoRequest.getIdProduct()))){
            throw new StockAlreadyExistsInTheDatabase(stockDtoRequest.getIdProduct());
        }
        System.out.println(stockMapper.toEntity(stockDtoRequest).getId());
        return stockMapper.toDto(stockRepository.save(stockMapper.toEntity(stockDtoRequest)));
    }
}

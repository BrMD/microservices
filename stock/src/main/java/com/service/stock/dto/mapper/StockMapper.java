package com.service.stock.dto.mapper;

import com.service.stock.dto.response.StockDto;
import com.service.stock.dto.request.StockDtoRequest;
import com.service.stock.entities.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public StockDto toDto(Stock stock){
        if(stock == null) return null;
        return new StockDto(stock.getId(), stock.getIdProduct(), stock.getQuantity());
    }

    public Stock toEntity(StockDto stockDto){
        if(stockDto == null) return null;
        return new Stock(stockDto.getId(), stockDto.getIdProduct(), stockDto.getQuantity());
    }

    public Stock toEntity(StockDtoRequest stockDtoRequest){
        if(stockDtoRequest == null) return null;
        return new Stock(stockDtoRequest.getIdProduct(), stockDtoRequest.getQuantity());
    }
}

package com.service.stock.exceptions;

public class StockNotFoundException extends IllegalStateException{
    public StockNotFoundException(String id){
        super(String.format("Stock with id %s not found", id));
    }
}

package com.service.stock.exceptions;

public class InsufficientStockException extends IllegalArgumentException{
    public InsufficientStockException(String id){
        super(String.format("Insufficient Stock, prod id: %s", id));
    }
}

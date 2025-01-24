package com.service.stock.exceptions;

public class StockAlreadyExistsInTheDatabase extends RuntimeException{
   public StockAlreadyExistsInTheDatabase(String id){
       super(String.format("Stock with id %s already exists", id));
   }
}

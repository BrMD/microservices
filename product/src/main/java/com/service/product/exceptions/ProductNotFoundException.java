package com.service.product.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String id){
        super(String.format("Demo with id %s, not found", id));
    }
}

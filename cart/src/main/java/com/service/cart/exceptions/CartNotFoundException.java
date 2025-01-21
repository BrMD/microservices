package com.service.cart.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String id){
        super(String.format("Cart with the id %s not found", id));
    }
}

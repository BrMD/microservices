package com.service.stock.controller;

import com.service.stock.exceptions.ApiErrorMessage;
import com.service.stock.exceptions.InsufficientStockException;
import com.service.stock.exceptions.StockAlreadyExistsInTheDatabase;
import com.service.stock.exceptions.StockNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        List<String> errors = new ArrayList<>();
        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = x.getDefaultMessage();
            errors.add(defaultMessage);
        }

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Object> handleDemoNotFound(
            InsufficientStockException exception, WebRequest request
    ){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatusCode.valueOf(400), exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(StockAlreadyExistsInTheDatabase.class)
    public ResponseEntity<Object> handleDemoNotFound(
            StockAlreadyExistsInTheDatabase exception, WebRequest request
    ){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatusCode.valueOf(409), exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<Object> handleDemoNotFound(
            StockNotFoundException exception, WebRequest request
    ){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatusCode.valueOf(404), exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }


}

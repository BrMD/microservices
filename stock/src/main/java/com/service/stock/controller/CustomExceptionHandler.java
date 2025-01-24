package com.service.stock.controller;

import com.service.stock.exceptions.ApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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

//    @ExceptionHandler(DemoNotFoundException.class)
//    public ResponseEntity<Object> handleDemoNotFound(
//            DemoNotFoundException exception, WebRequest request
//    ){
//        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatusCode.valueOf(404), exception.getMessage());
//        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
//    }


}

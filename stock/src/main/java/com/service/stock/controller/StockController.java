package com.service.stock.controller;

import com.service.stock.dto.request.StockDtoRequest;
import com.service.stock.dto.response.StockDto;
import com.service.stock.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    private final CreateStockImpl createProductImpl;
    private final DeleteStockImpl deleteStockImpl;
    private final GetStockQuantityImpl getStockQuantityImpl;
    private final PutDecreaseImpl putDecreaseImpl;
    private final PutIncreaseImpl putIncreaseImpl;

    public StockController(CreateStockImpl createProductImpl, DeleteStockImpl deleteStockImpl,
                           GetStockQuantityImpl getStockQuantityImpl, PutDecreaseImpl putDecreaseImpl,
                           PutIncreaseImpl putIncreaseImpl) {
        this.createProductImpl = createProductImpl;
        this.deleteStockImpl = deleteStockImpl;
        this.getStockQuantityImpl = getStockQuantityImpl;
        this.putDecreaseImpl = putDecreaseImpl;
        this.putIncreaseImpl = putIncreaseImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStock(@PathVariable String id){
        StockDto stockDto = getStockQuantityImpl.getQuantity(id);
        return new ResponseEntity<>(stockDto, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable String id){
        deleteStockImpl.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PostMapping()
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDtoRequest stockDtoRequest){
        StockDto stockDto = createProductImpl.create(stockDtoRequest);
        return new ResponseEntity<>(stockDto, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/inc/{id}")
    public ResponseEntity<Void> increaseStock(@PathVariable String id, @RequestParam Integer quantity){
        putIncreaseImpl.incQuantity(id, quantity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PutMapping("/dec/{id}")
    public ResponseEntity<Void> decreaseStock(@PathVariable String id, @RequestParam Integer quantity){
        putDecreaseImpl.decQuantity(id, quantity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}

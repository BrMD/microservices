package com.service.stock.repositories;

import com.service.stock.entities.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, String> {
    Optional<Stock> findByIdProduct(String id);
    Boolean existsByIdProduct(String id);
}

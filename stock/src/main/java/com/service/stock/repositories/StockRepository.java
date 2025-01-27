package com.service.stock.repositories;

import com.service.stock.entities.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends CrudRepository<Stock, UUID> {
    Optional<Stock> findByIdProduct(String id);
    Boolean existsByIdProduct(String id);
}

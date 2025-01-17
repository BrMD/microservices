package com.service.product.controller;

import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.response.ProductDto;
import com.service.product.serviceimpl.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final GetAllProductsUseCaseImpl getAllProductsUseCase;
    private final CreateProductImpl createProductImpl;
    private final DeleteProductImpl deleteProductImpl;
    private final FindProductByIdImpl findProductByIdImpl;
    private final UpdateByIdProductImpl updateByIdProductImpl;

    @Autowired
    public ProductsController(GetAllProductsUseCaseImpl getAllProductsUseCase, CreateProductImpl createProductImpl,
                              DeleteProductImpl deleteProductImpl, FindProductByIdImpl findProductByIdImpl,
                              UpdateByIdProductImpl updateByIdProductImpl) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.createProductImpl = createProductImpl;
        this.deleteProductImpl = deleteProductImpl;
        this.findProductByIdImpl = findProductByIdImpl;
        this.updateByIdProductImpl = updateByIdProductImpl;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> productDto = getAllProductsUseCase.getAll();
        return new ResponseEntity<>(productDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> create(@Valid @RequestBody RequestProductDto requestProductDto){
        ProductDto productDto = createProductImpl.create(requestProductDto);
        return new ResponseEntity<>(productDto,HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteProductImpl.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable String id){
        ProductDto productDto = findProductByIdImpl.findById(id);
        return new ResponseEntity<>(productDto, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody RequestProductDto requestProductDto){
        updateByIdProductImpl.update(id, requestProductDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
}

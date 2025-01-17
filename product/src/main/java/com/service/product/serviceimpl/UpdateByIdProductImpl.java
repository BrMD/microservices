package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.request.RequestProductDto;
import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.UpdateByIdProductUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UpdateByIdProductImpl implements UpdateByIdProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public UpdateByIdProductImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void update(String id, RequestProductDto requestProductDto){
        var updateProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        updateProduct.setName(requestProductDto.getName());
        updateProduct.setDescription(requestProductDto.getDescription());
        updateProduct.setPrice(requestProductDto.getPrice());
        updateProduct.setStock(requestProductDto.getStock());
        updateProduct.setImageUrl(requestProductDto.getImageUrl());

        productRepository.save(updateProduct);
    }
}

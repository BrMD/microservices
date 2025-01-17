package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.response.ProductDto;
import com.service.product.entity.Product;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.CreateProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class CreateProductImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public CreateProductImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto create(RequestProductDto requestProductDto){
        Product product = productRepository.save(productMapper.toEntity(requestProductDto));
        return productMapper.toDto(product);
    }
}

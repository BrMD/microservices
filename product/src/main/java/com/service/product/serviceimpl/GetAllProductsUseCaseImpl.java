package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.response.ProductDto;
import com.service.product.usecase.GetAllProductsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.service.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public GetAllProductsUseCaseImpl(ProductRepository productRepository,ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getAll(){
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());

    }
}

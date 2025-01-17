package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.response.ProductDto;
import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.FindProducByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class FindProductByIdImpl implements FindProducByIdUseCase {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public FindProductByIdImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto findById(String id){
        var productFound = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDto(productFound);
    }
}

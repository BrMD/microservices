package com.service.product.usecase;

import com.service.product.dto.response.ProductDto;

public interface FindProducByIdUseCase {
    public ProductDto findById(String id);
}

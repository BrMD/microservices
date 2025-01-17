package com.service.product.usecase;

import com.service.product.dto.response.ProductDto;

import java.util.List;

public interface GetAllProductsUseCase {
    public List<ProductDto> getAll();
}

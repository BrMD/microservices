package com.service.product.usecase;

import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.response.ProductDto;

public interface CreateProductUseCase {
    public ProductDto create(RequestProductDto requestProductDto);
}

package com.service.product.usecase;

import com.service.product.dto.request.RequestProductDto;

public interface UpdateByIdProductUseCase {
    public void update(String id, RequestProductDto requestProductDto);
}

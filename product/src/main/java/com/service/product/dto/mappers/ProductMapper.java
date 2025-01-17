package com.service.product.dto.mappers;

import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.response.ProductDto;
import com.service.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product){
        if(product == null) return null;
        return new ProductDto(product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getImageUrl());
    }

    public Product toEntity(RequestProductDto requestProductDto){
        if(requestProductDto == null) return null;
        return new Product(requestProductDto.getName(), requestProductDto.getDescription(), requestProductDto.getPrice(), requestProductDto.getStock(), requestProductDto.getImageUrl());
    }

    public Product toEntity(ProductDto productDto){
        if(productDto == null) return null;
        return new Product(productDto.getProductId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getStock(), productDto.getImageUrl());
    }
}

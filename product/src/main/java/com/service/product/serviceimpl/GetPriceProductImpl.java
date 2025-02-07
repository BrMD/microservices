package com.service.product.serviceimpl;

import com.service.product.entity.Product;
import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.GetPriceProductUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetPriceProductImpl implements GetPriceProductUseCase {
    private final ProductRepository productRepository;

    public GetPriceProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Float getPrice(String id){
        Product productFinded = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productFinded.getPrice();
    }
}

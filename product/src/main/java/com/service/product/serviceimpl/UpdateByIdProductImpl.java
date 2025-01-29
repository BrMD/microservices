package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.request.RequestStockCreate;
import com.service.product.dto.response.StockDto;
import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.UpdateByIdProductUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Validated
public class UpdateByIdProductImpl implements UpdateByIdProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public UpdateByIdProductImpl(ProductRepository productRepository, ProductMapper productMapper,
                                 DiscoveryClient discoveryClient, RestClient restClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public void update(String id, RequestProductDto requestProductDto){
        var updateProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ServiceInstance stockInstance = discoveryClient.getInstances("stock").getFirst();
        String finalUri = UriComponentsBuilder.fromUri(stockInstance.getUri()).path("/edit/" + id)
                .queryParam("quantity", requestProductDto.getStock()).build(id).toString();
        restClient.put()
                .uri(finalUri) // Substitui {id} pelo valor real
                .retrieve()
                .toBodilessEntity();
        updateProduct.setName(requestProductDto.getName());
        updateProduct.setDescription(requestProductDto.getDescription());
        updateProduct.setPrice(requestProductDto.getPrice());
        updateProduct.setImageUrl(requestProductDto.getImageUrl());

        productRepository.save(updateProduct);
    }
}

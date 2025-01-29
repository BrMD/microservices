package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.request.RequestProductDto;
import com.service.product.dto.request.RequestStockCreate;
import com.service.product.dto.response.ProductDto;
import com.service.product.dto.response.StockDto;
import com.service.product.entity.Product;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.CreateProductUseCase;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

@Validated
@Service
public class CreateProductImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public CreateProductImpl(ProductRepository productRepository, ProductMapper productMapper,
                             DiscoveryClient discoveryClient, RestClient restClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public ProductDto create(RequestProductDto requestProductDto){
        Product product = productRepository.save(productMapper.toEntity(requestProductDto));
        ServiceInstance stockInstance = discoveryClient.getInstances("stock").getFirst();
        RequestStockCreate stock = new RequestStockCreate(product.getProductId(), requestProductDto.getStock());
        StockDto response = restClient
                .post().uri(stockInstance.getUri())
                .body(stock).retrieve().body(StockDto.class);
        ProductDto responseProductDto = productMapper.toDto(product);
        try{
            if(response!=null){
                responseProductDto.setStock(response.getQuantity());
            }
        }catch (Exception e){
            throw e;
        }
        return responseProductDto;
    }
}

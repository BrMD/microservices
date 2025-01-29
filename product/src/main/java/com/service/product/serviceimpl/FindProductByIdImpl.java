package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.request.RequestStockCreate;
import com.service.product.dto.response.ProductDto;
import com.service.product.dto.response.StockDto;
import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.FindProducByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

@Service
@Validated
public class FindProductByIdImpl implements FindProducByIdUseCase {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public FindProductByIdImpl(ProductMapper productMapper, ProductRepository productRepository,
                               DiscoveryClient discoveryClient, RestClient restClient) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public ProductDto findById(String id){
        var productFound = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ServiceInstance stockInstance = discoveryClient.getInstances("stock").getFirst();
        StockDto response = restClient
                .get().uri(stockInstance.getUri() + "/" + id)
                .retrieve().body(StockDto.class);
        ProductDto productDto = productMapper.toDto(productFound);
        try{
            if(response!=null){
                productDto.setStock(response.getQuantity());
            }
        }catch (Exception e){
            throw e;
        }
        return productDto;
    }
}

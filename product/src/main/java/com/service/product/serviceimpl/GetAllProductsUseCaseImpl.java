package com.service.product.serviceimpl;

import com.service.product.dto.mappers.ProductMapper;
import com.service.product.dto.response.ProductDto;
import com.service.product.dto.response.StockDto;
import com.service.product.entity.Product;
import com.service.product.usecase.GetAllProductsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.service.product.repository.ProductRepository;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public GetAllProductsUseCaseImpl(ProductRepository productRepository,ProductMapper productMapper,
                                     DiscoveryClient discoveryClient, RestClient restClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public List<ProductDto> getAll(){
        return productRepository.findAll().stream().map(this::getStockFromService).collect(Collectors.toList());
    }

    public ProductDto getStockFromService(Product product) {
        ProductDto productDto = productMapper.toDto(product);
        try{
            ServiceInstance stockInstance = discoveryClient.getInstances("stock").getFirst();
            StockDto response = restClient
                    .get().uri(stockInstance.getUri() + "/" + productDto.getProductId())
                    .retrieve().body(StockDto.class);

            productDto.setStock(response.getQuantity());
            return productDto;
        }catch (Exception e){
            throw e;
        }
    }

}

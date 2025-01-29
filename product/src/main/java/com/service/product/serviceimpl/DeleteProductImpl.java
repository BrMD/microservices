package com.service.product.serviceimpl;

import com.service.product.exceptions.ProductNotFoundException;
import com.service.product.repository.ProductRepository;
import com.service.product.usecase.DeleteProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

@Validated
@Service
public class DeleteProductImpl implements DeleteProductUseCase {
    private final ProductRepository productRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public DeleteProductImpl(ProductRepository productRepository, DiscoveryClient discoveryClient, RestClient restClient) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
        this.productRepository = productRepository;
    }

    @Override
    public void delete(String id){
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ServiceInstance stockInstance = discoveryClient.getInstances("stock").getFirst();
        restClient.delete().uri(stockInstance.getUri() + "/" + id).retrieve().toBodilessEntity();
        productRepository.deleteById(id);
    }
}

package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.dto.response.CartDto;
import com.service.cart.dto.response.ProductDto;
import com.service.cart.dto.response.ProductDtoResponse;
import com.service.cart.exceptions.CartNotFoundException;
import com.service.cart.hash.Cart;
import com.service.cart.hash.Product;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.GetCartUseCase;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GetCartImpl implements GetCartUseCase {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public GetCartImpl(CartRepository cartRepository, CartMapper cartMapper, DiscoveryClient discoveryClient, RestClient restClient) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public CartDto getCart(String id){
        Cart cartFinded = cartRepository.findByUserId(id).orElseThrow(() -> new CartNotFoundException(id));
        List<ProductDtoResponse> productDtosResponse = cartFinded.getProducts().stream().map(this::getProducts).toList();
        return cartMapper.toDto(cartFinded, productDtosResponse);
    }

    private ProductDtoResponse getProducts(Product product){
        ServiceInstance productInstance = discoveryClient.getInstances("products").getFirst();
        ProductDto productDto = restClient.get().uri(productInstance.getUri() + "/api/v1/products/" + product.getProductId()).retrieve().body(ProductDto.class);
        return new ProductDtoResponse(productDto.getProductId(), product.getQuantity(), productDto.getName(), productDto.getImageUrl());
    }
}

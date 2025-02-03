package com.service.cart.service;

import com.service.cart.dto.CartMapper;
import com.service.cart.dto.request.CartDtoRequest;
import com.service.cart.dto.response.ProductDto;
import com.service.cart.dto.response.ProductDtoResponse;
import com.service.cart.dto.response.PutOrCreateDtoResponse;
import com.service.cart.exceptions.ProductNotFoundException;
import com.service.cart.hash.Cart;
import com.service.cart.hash.Product;
import com.service.cart.repositories.CartRepository;
import com.service.cart.usecase.PutOrCreateCartUseCase;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PutOrCreateImpl implements PutOrCreateCartUseCase {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public PutOrCreateImpl(CartRepository cartRepository, CartMapper cartMapper, DiscoveryClient discoveryClient, RestClient restClient) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public PutOrCreateDtoResponse putOrCreate(String userId, CartDtoRequest cartDtoRequest){
        PutOrCreateDtoResponse putOrCreateDtoResponse = new PutOrCreateDtoResponse();
        putOrCreateDtoResponse.setCode(200);
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() ->{
            putOrCreateDtoResponse.setCode(201);
            return create(userId);
        });

        cart.addProduct(new Product(cartDtoRequest.getProductId(), cartDtoRequest.getQuantity()));
        List<ProductDtoResponse> productDtosResponse = cart.getProducts().stream().map(this::getProductsApi).toList();
        putOrCreateDtoResponse.setCartDto(cartMapper.toDto(cartRepository.save(cart), productDtosResponse));
        return putOrCreateDtoResponse;
    }

    public Cart create(String userId){
        Cart cartToBeSaved = new Cart();
        cartToBeSaved.setUserId(userId);
        cartToBeSaved.setProducts(new ArrayList<>());
        return cartRepository.save(cartToBeSaved);
    }

    private ProductDtoResponse getProductsApi(Product product){
        ServiceInstance productInstance = discoveryClient.getInstances("products").getFirst();
        ProductDto productDto = restClient.get().uri(productInstance.getUri() + "/api/v1/products/" + product.getProductId())
                .retrieve().body(ProductDto.class);
        if(productDto == null) throw new ProductNotFoundException(product.getProductId());
        return new ProductDtoResponse(productDto.getProductId(), product.getQuantity(), productDto.getName(), productDto.getImageUrl());
    }
}

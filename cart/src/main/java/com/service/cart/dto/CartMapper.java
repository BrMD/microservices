package com.service.cart.dto;

import com.service.cart.dto.response.CartDto;
import com.service.cart.dto.response.ProductDtoResponse;
import com.service.cart.hash.Cart;
import com.service.cart.hash.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {
    public CartDto toDto(Cart cart){
        if(cart == null) return null;
        return new CartDto(cart.getId(), cart.getUserId(), cart.getProducts().stream().map(this::mapProductsToDto).collect(Collectors.toList()));
    }

    public Cart toHash(CartDto cartDto){
        if(cartDto == null) return null;
        return new Cart(cartDto.getId(), cartDto.getUserId(), cartDto.getProductDtoResponse().stream().map(this::mapProductToHash).collect(Collectors.toList()));
    }

    public ProductDtoResponse mapProductsToDto(Product product){
        if(product == null)return null;
        //#TODO nao esquecer de trocar nome e imagem do produto por uma chamada http pra pegar esse nome e image
        return new ProductDtoResponse(product.getProductId(), product.getQuantity(), "nomedoproduto", "imagemdoProduto");
    }

    public Product mapProductToHash(ProductDtoResponse productDtoResponse){
        if(productDtoResponse == null)return null;
        return new Product(productDtoResponse.getProductId(), productDtoResponse.getQuantity());
    }
}

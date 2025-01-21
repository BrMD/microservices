package com.service.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {
    private String productId;
    private Integer quantity;
    private String prodName;
    private String imageUrl;
}

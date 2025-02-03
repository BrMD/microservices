package com.service.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDtoResponse {
    private String id;
    private Integer quantity;
    private String name;
    private String image;
}

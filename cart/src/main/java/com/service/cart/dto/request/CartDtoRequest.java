package com.service.cart.dto.request;

import com.service.cart.hash.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDtoRequest {
    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "Product cannot be null")
    private Product product;

}

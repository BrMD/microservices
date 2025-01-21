package com.service.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.cart.hash.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    @NotNull(message = "Id cannot be null")
    @JsonProperty("id")
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "Cart cannot be null")
    private List<ProductDtoResponse> productDtoResponse;
}

package com.service.cart.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDtoRequest {
    @NotNull(message = "Product cannot be null")
    @Size(min = 1, message = "productId must be greater than 1")
    private String productId;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater or equal than 0")
    private Integer quantity;
}

package com.service.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStockCreate {
    @NotNull(message = "IdProduct cannot be null")
    @Size(min = 1, message = "IdProduct must be greater than 1")
    private String idProduct;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater or equal to 1")
    private Integer quantity;
}

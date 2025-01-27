package com.order.order.dto.response;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull(message = "Id cannot be null")
    @Size(message = "Id must be greater than one")
    private UUID id;

    @NotNull(message = "userId cannot be null")
    @Size(message = "userId must be greater than one")
    private UUID userId;

    @NotNull(message = "cartId cannot be null")
    @Size(message = "cartId must be greater than one")
    private String cartId;

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.01", message = "Value must be greater than 0.01")
    private Double value;
}

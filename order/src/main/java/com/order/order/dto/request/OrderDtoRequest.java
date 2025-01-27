package com.order.order.dto.request;

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
public class OrderDtoRequest {
    @NotNull(message = "userId cannot be null")
    private UUID userId;

    @NotNull(message = "cartId cannot be null")
    @Size(message = "cartId must be greater than one")
    private String cartId;

}

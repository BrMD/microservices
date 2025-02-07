package com.order.order.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, message = "Id must be greater than 1")
    private String id;

    @NotNull(message = "userId cannot be null")
    @Size(min = 1, message = "userId must be greater than 1")
    private String userId;

    @NotNull(message = "userId cannot be null")
    private List<ProductDtoResponse> productsDto;

}

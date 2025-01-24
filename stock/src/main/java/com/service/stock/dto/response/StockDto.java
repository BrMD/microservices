package com.service.stock.dto.response;

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
public class StockDto {
    @NotNull(message = "Id cannot be null")
    @Size(min = 1, message = "Id must be greater than 1")
    private String id;

    @NotNull(message = "IdProduct cannot be null")
    @Size(min = 1, message = "IdProduct must be greater than 1")
    private String IdProduct;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater or equal to 1")
    private Integer quantity;
}

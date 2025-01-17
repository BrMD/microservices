package com.service.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "Id cannot be null")
    @JsonProperty("productId")
    private String productId;

    @NotNull(message = "Name Cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "Description Cannot be null")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;

    @NotNull(message = "Price Cannot be null")
    @Positive
    private Float price;

    @NotNull(message = "Stock cannot be null")
    private String stock;

    @NotNull(message = "ImageUrl Cannot be null")
    @Size(min = 1, max = 255, message = "imageUrl must be between 1 and 255 characters")
    private String imageUrl;
}

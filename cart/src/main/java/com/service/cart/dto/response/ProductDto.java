package com.service.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1, message = "Stock must be greater or equal than 1")
    private Integer stock;

    @NotNull(message = "ImageUrl Cannot be null")
    @Size(min = 1, max = 255, message = "imageUrl must be between 1 and 255 characters")
    private String imageUrl;

    public ProductDto(String productId, String name, Float price, String description, String imageUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}

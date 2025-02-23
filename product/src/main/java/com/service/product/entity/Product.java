package com.service.product.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "products")
@EqualsAndHashCode
public class Product {
    @Id
    @MongoId
    private String productId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Positive
    private Float price;

    @NotBlank
    private String imageUrl;

    public Product(String name, String description, Float price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}

package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.product.ProductRequestDTO;
import com.vinibortoletto.simpleshop.models.Category;
import com.vinibortoletto.simpleshop.models.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProductFaker {
    private final Faker faker = new Faker();
    private final CategoryFaker categoryFaker = new CategoryFaker();

    public Product createFakeProduct() {
        Product fakeProduct = new Product();
        Category category = categoryFaker.createFakeCategory();

        fakeProduct.setId(String.valueOf(UUID.randomUUID()));
        fakeProduct.setName(faker.commerce().productName());
        fakeProduct.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
        fakeProduct.setStock(faker.number().numberBetween(1, 100));
        fakeProduct.setImage(faker.internet().image());
        fakeProduct.setDescription(faker.lorem().sentence());
        fakeProduct.getCategories().add(category);

        return fakeProduct;
    }

    public ProductRequestDTO createFakeProductRequestDTO() {
        List<String> categories = Arrays.asList(String.valueOf(UUID.randomUUID()), String.valueOf(UUID.randomUUID()));

        return new ProductRequestDTO(
            faker.commerce().productName(),
            BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)),
            faker.number().numberBetween(1, 100),
            faker.internet().image(),
            faker.lorem().sentence(),
            categories
        );
    }
}

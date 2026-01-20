package com.comcast.payloads;

import com.comcast.pojo.Product;
import com.github.javafaker.Faker;

import java.util.Random;

public class Payload {

    private static final Faker faker = new Faker();
    private static final String[] categories = {"electronics", "furniture", "clothing", "books", "beauty"};
    private static Random random = new Random();
    // Product

    public static Product getProductPayload() {
        String name = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        String description = faker.lorem().sentence();
        String imageUrl = "https://i.pravatar.cc/100";
        String category = categories[random.nextInt(categories.length)];

        return Product.builder()
                      .title(name)
                      .price(price)
                      .description(description)
                      .image(imageUrl)
                      .category(category)
                      .build();
    }

    // Cart

    // User

    // Login


}

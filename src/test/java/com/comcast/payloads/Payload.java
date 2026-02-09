package com.comcast.payloads;

import com.comcast.pojo.product.Product;
import com.comcast.pojo.user.Address;
import com.comcast.pojo.user.Geolocation;
import com.comcast.pojo.user.Name;
import com.comcast.pojo.user.User;
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

    public static User getUserPayLoad() {
        return User.builder()
                .name(
                        Name.builder()
                                .firstname(faker.address().firstName())
                                .lastname(faker.address().lastName())
                                .build()
                )
                .address(
                        Address.builder()
                                .city(faker.address().city())
                                .number(faker.number().numberBetween(10001, 99999))
                                .street(faker.address().streetAddress())
                                .zipcode(faker.address().zipCode())
                                .build()
                )
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().toString())
                .password(faker.internet().password())
                .geolocation(
                        Geolocation.builder()
                                .lat(faker.address().latitude())
                                .lon(faker.address().longitude())
                                .build()
                ).build();
    }

    // Login


}

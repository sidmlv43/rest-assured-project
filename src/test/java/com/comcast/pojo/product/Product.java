package com.comcast.pojo.product;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private int id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private Rating rating;
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Rating {
    private double rate;
    private int count;
}
package com.comcast.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}

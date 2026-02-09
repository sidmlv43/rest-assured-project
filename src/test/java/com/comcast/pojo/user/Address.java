package com.comcast.pojo.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Address {
    private String city;
    private String street;
    private int number;
    private String zipcode;
}

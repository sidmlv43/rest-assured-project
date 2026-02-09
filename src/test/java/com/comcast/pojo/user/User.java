package com.comcast.pojo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties({"address", "geolocation", "phone", "name"})
public class User {
    private String id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private Address address;
    private Geolocation geolocation;
    private String phone;
    private String __v;
}





package com.comcast.pojo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Geolocation {
    private String lat;
    @JsonProperty("long")
    private String lon;
}

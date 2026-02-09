package com.comcast.pojo.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Name {
    private String firstname;
    private String lastname;
}

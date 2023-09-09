package com.bruce.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private String password;
    private String salt;

    public static User build(String name, String password) {
        // todo
        return new User(name, password, "salt");
    }
}

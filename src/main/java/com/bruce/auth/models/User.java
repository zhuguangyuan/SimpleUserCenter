package com.bruce.auth.models;

import com.bruce.auth.utils.CryptUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private String password;
    private String salt;

    public static User build(String name, String password) {
        User user = new User();
        user.setName(name);

        String salt = CryptUtils.generateSalt(8);
        user.setSalt(salt);

        String pass = CryptUtils.encrypt(password, salt);
        user.setPassword(pass);

        return user;
    }

    public boolean checkPassCorrect(String password) {
        String p2 = CryptUtils.encrypt(password, this.getSalt());
        return Objects.equals(this.getPassword(), p2);
    }
}

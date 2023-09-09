package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        String name = "bruce";
        String password = "myPassWord";
        Assertions.assertNotNull(userService.createUser(name, password));

        // Should fail if the user already exists
        Assertions.assertThrows(AuthException.class, () -> userService.createUser(name, password));

        // Password-to be stored in some encrypted form
    }

    @Test
    void testDelUser() {
        String name = "bruce";
        String password = "myPassWord";
        userService.createUser(name, password);
        Assertions.assertNotNull(userService.delUser(name));

        // Should fail if the user doesn't exist
        Assertions.assertThrows(AuthException.class, () -> userService.delUser("bruce2"));
    }
}
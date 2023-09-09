package com.bruce.auth.services;

import com.bruce.auth.models.TokenInfo;
import com.bruce.auth.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(authService, "tokenTTL", "1000");
    }

    @Test
    void login() {
        // return a special "secret" auth token or error, if not found.
        String name = "bruce";
        String pass = "myPass";
        Mockito.when(userService.getWithPassword(name, pass)).thenReturn(User.builder().name(name).build());
        String token = authService.login(name, pass);
        Assertions.assertNotNull(token);

        TokenInfo tokenInfo = authService.getByCheck(token);
        Assertions.assertNotNull(tokenInfo);

        // The token is only valid for pre-configured time(2h)
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException ignore) {
        }
        tokenInfo = authService.getByCheck(token);
        Assertions.assertNull(tokenInfo);
    }

    @Test
    void logout() {
        String name = "bruce";
        String pass = "myPass";
        Mockito.when(userService.getWithPassword(name, pass)).thenReturn(User.builder().name(name).build());
        String token = authService.login(name, pass);
        Assertions.assertNotNull(token);

        // Handles correctly the case of invalid token given as input
        authService.logout("token");
        authService.logout(null);

        // returns nothing. the token is no longer valid after the call.
        authService.logout(token);
        TokenInfo tokenInfo = authService.getByCheck(token);
        Assertions.assertNull(tokenInfo);
    }
}
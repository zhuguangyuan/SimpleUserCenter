package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;

    @Test
    void createRole() {
        String name = "role1";
        Assertions.assertNotNull(roleService.createRole(name));

        // Should fail if the role already exists
        Assertions.assertThrows(AuthException.class, () -> roleService.createRole(name));
    }

    @Test
    void delRole() {
        String name = "role1";
        roleService.createRole(name);
        Assertions.assertNotNull(roleService.delRole(name));

        // Should fail if the role doesn't exist
        Assertions.assertThrows(AuthException.class, () -> roleService.delRole(name));
    }
}
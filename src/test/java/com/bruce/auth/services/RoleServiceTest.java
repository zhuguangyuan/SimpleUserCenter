package com.bruce.auth.services;

import com.bruce.auth.exceptions.AuthException;
import com.bruce.auth.models.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;
    @Mock
    private UserRoleService userRoleService;

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
        Role role = roleService.createRole(name);
        Assertions.assertNotNull(roleService.delRole(name));

        // Should fail if the role doesn't exist
        Assertions.assertThrows(AuthException.class, () -> roleService.delRole(name));

        // if role in use should fail too.
        role = roleService.createRole(name);
        roleService.setRoleInUse(role);
        Assertions.assertThrowsExactly(AuthException.class, () -> roleService.delRole(name));
    }
}
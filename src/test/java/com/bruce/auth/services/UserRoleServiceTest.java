package com.bruce.auth.services;

import com.bruce.auth.models.Role;
import com.bruce.auth.models.TokenInfo;
import com.bruce.auth.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRoleServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private RoleService roleService;
    @Mock
    private AuthService authService;
    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    void addRoleToUser() {
        String userName = "bruce";
        String password = "password";
        String roleName = "role1";
        String roleName2 = "role2";
        Mockito.when(userService.getUser(userName)).thenReturn(User.build(userName, password));
        Mockito.when(roleService.getRole(roleName)).thenReturn(Role.builder().name(roleName).build());
        Mockito.when(roleService.getRole(roleName2)).thenReturn(Role.builder().name(roleName2).build());

        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName));

        // If the role is already associated with the user,nothing should happen
        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName));
        Assertions.assertEquals(userRoleService.addRoleToUser(userName, roleName).size(), 1);

        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName2));
    }

    @Test
    void checkRole() {
        // user has role1
        String userName = "bruce";
        String password = "password";
        String roleName = "role1";
        String roleName2 = "role2";
        Mockito.when(userService.getUser(userName)).thenReturn(User.build(userName, password));
        Mockito.when(userService.getUser(userName)).thenReturn(User.builder().name(userName).build());
        Mockito.when(roleService.getRole(roleName)).thenReturn(Role.builder().name(roleName).build());
        Mockito.when(roleService.getRole(roleName2)).thenReturn(Role.builder().name(roleName2).build());
        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName));

        // user login
        String token = "token1";
        Mockito.when(authService.getByCheck(token)).thenReturn(TokenInfo.builder()
                .token(token)
                .expireTime(System.currentTimeMillis() + 1000L)
                .userName(userName)
                .build());
        // returns true if the user,identified by the token,belongs to the role,
        Assertions.assertTrue(userRoleService.checkHasRole(token, "role1"));
        Assertions.assertFalse(userRoleService.checkHasRole(token, "role2"));

        // false otherwise; error if token is invalid expired etc
    }

    @Test
    void getAllRoles() {
        // user has role1 & role2
        String userName = "bruce";
        String password = "password";
        String roleName = "role1";
        String roleName2 = "role2";
        Mockito.when(userService.getUser(userName)).thenReturn(User.build(userName, password));
        Mockito.when(roleService.getRole(roleName)).thenReturn(Role.builder().name(roleName).build());
        Mockito.when(roleService.getRole(roleName2)).thenReturn(Role.builder().name(roleName2).build());
        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName));
        Assertions.assertDoesNotThrow(() -> userRoleService.addRoleToUser(userName, roleName2));

        // user login
        String token = "token1";
        Mockito.when(authService.getByCheck(token)).thenReturn(TokenInfo.builder()
                .token(token)
                .expireTime(System.currentTimeMillis() + 1000L)
                .userName(userName)
                .build());

        // returns all roles for the user, error if token is invalid
        List<Role> roles = userRoleService.getAllRoles(token);
        Assertions.assertEquals(2, roles.size());
    }
}
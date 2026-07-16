package com.mol211.deliveryrice.user;

import com.mol211.deliveryrice.user.model.Role;
import com.mol211.deliveryrice.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static com.mol211.deliveryrice.utils.TestingData.crearUser001;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEntityTest {
    User user = null;
    @BeforeEach
    void setUp() {
        user = crearUser001();
    }

    @Test
    void testGetUsername() {
        String mail = "victor@mail.com";
        assertEquals(mail,user.getUsername());
    }

    @Test
    void testGetAuthorities() {
        assertEquals(1, user.getAuthorities().size());
        assertTrue(
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
                        .contains("ADMIN")
        );

    }
}

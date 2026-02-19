package tech.buildrun.livechatms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.buildrun.livechatms.model.Users;
import tech.buildrun.livechatms.repository.UserRepo;

import tech.buildrun.livechatms.service.UserService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void setup() {
        userRepo.deleteAll(); // limpar DB antes de cada teste
    }

    @Test
    void testRegisterNewUser() {
        boolean success = userService.register("testuser", "password123");
        assertTrue(success);

        Users saved = userRepo.findByUsername("testuser").orElse(null);
        assertNotNull(saved);
        assertNotEquals("password123", saved.getPassword()); // senha deve estar criptografada
    }

    @Test
    void testRegisterDuplicateUser() {
        userService.register("testuser", "password123");
        boolean success = userService.register("testuser", "anotherPass");
        assertFalse(success);
    }

    @Test
    void testPasswordVerification() {
        userService.register("testuser", "password123");
        Users user = new Users("testuser", "password123");
        assertTrue(userService.verify(user));
    }

    @Test
    void testVerifyWrongPassword() {
        userService.register("testuser", "password123");
        Users user = new Users("testuser", "wrongpass");
        assertThrows(IllegalArgumentException.class, () -> userService.verify(user));
    }
}
package edu.school21.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.school21.service.config.TestApplicationConfig;


@SpringBootTest(classes = TestApplicationConfig.class)
public class UsersServiceImplTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void testSignUp() {
        String email = "test@example.com";
        String temporaryPassword = usersService.signUp(email);
        Assertions.assertNotNull(temporaryPassword);
    }
}

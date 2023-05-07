package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class AppTests {

    @Test
    @WithMockUser(username = "user1", roles = "admin")
    void contextLoads() {
    }

}

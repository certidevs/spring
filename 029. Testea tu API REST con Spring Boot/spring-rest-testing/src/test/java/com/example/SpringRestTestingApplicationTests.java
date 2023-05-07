package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.SqlGroup;

@Rollback
@SpringBootTest
class SpringRestTestingApplicationTests {

    @Test
    void contextLoads() {
    }

}

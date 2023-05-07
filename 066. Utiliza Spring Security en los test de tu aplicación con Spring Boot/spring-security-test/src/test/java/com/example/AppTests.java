package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void test1() throws Exception {
        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void test2() throws Exception {
        mvc.perform(get("/api/method1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
//    @WithMockUser
    @WithMockUser(username = "user2", password = "password", roles = {"ADMIN"})
    void test3() throws Exception {
        mvc.perform(get("/api/method1"))
                .andExpect(status().isOk())
                .andExpect(content().string("method1"));
    }

    @Test
    void test4() throws Exception {
        mvc.perform(get("/api/method1")
                        .with(user("user1").password("password").roles("read"))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("method1"));
    }

    @Test
    @WithUserDetails("usertest")
    void test5() throws Exception {
        mvc.perform(get("/api/method1"))
                .andExpect(status().isOk())
                .andExpect(content().string("method1"));
    }

    @Test
    void test6() throws Exception {
        // contraseña incorrecta
        mvc.perform(get("/api/method1").with(httpBasic("user1", "wrongpassword")))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void test7() throws Exception {
        // contraseña correcta
        mvc.perform(get("/api/method1").with(httpBasic("user1", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("method1"));
    }
    @Test
    void test8() throws Exception {
        // contraseña correcta
        mvc.perform(formLogin().user("user1").password("password"))
                .andExpect(authenticated());
    }

}

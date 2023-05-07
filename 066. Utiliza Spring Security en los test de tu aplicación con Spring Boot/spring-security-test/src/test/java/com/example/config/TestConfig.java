package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Descomentar para cargar configuración de test que reemplace la configuración de producción
//@Configuration
public class TestConfig {

    @Primary
    @Bean("userDetailsServiceTest")
    public UserDetailsService uesrDetailsService(PasswordEncoder passwordEncoder){
        var userDetailsService = new InMemoryUserDetailsManager();
        var user1 = User.builder()
                .username("usertest")
                .password(passwordEncoder.encode("password"))
                .authorities("read")
                .build();
        userDetailsService.createUser(user1);
        return userDetailsService;
    }
}

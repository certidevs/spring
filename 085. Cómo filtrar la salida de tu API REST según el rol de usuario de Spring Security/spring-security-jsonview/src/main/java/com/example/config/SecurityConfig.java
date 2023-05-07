package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user1")
                        .password(passwordEncoder().encode("user1"))
                        .roles("ADMIN")
                        .build());
        manager.createUser(User.withUsername("user2")
                .password(passwordEncoder().encode("user2"))
                .roles("MANAGER")
                .build());
        manager.createUser(User.withUsername("user3")
                .password(passwordEncoder().encode("user3"))
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var service = new InMemoryUserDetailsManager();

//        var u1 = User.withUsername("user1")
//                .password(passwordEncoder().encode("amin"))
//                .authorities("read")
//                .build();
//
//        var u2 = User.withUsername("user2")
//                .password(passwordEncoder().encode("amin"))
//                .authorities("write")
//                .build();
        var u1 = User.withUsername("user1")
                .password(passwordEncoder().encode("amin"))
                .roles("ADMIN")
                .build();

        var u2 = User.withUsername("user2")
                .password(passwordEncoder().encode("amin"))
                .roles("USER")
                .build();

        service.createUser(u1);
        service.createUser(u2);
        return service;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

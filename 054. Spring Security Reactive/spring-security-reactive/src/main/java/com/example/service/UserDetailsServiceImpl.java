package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // tambien se puede extraer de base de datos con ayuda de
        // un repositorio reactivo con R2DBC o MongoDB Reactive
        return Mono.just(
                User.withUsername("admin2")
                .password(passwordEncoder.encode("admin2"))
                .roles("ADMIN")
                .build()
        );
    }
}

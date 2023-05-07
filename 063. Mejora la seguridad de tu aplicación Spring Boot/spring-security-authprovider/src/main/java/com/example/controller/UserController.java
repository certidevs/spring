package com.example.controller;

import com.example.dto.RegisterDTO;
import com.example.model.UserAuthority;
import com.example.model.UserEntity;
import com.example.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController

public class UserController {

    @Autowired
    private UserEntityRepository repo;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/auth/register")
    public void register(@RequestBody RegisterDTO register,
                         HttpServletRequest request){

        System.out.println("register");

        repo.findByUsername(register.username()).ifPresent(user -> {
            throw new RuntimeException("Username already exists");
        });

        repo.findByEmail(register.email()).ifPresent(user -> {
            throw new RuntimeException("Email already exists");
        });

        UserEntity user = register.toUserEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        // Personalización de la seguridad por medio de comprobar la IP
        String ip = request.getRemoteAddr();
        user.getIps().add(ip);
        repo.save(user);
    }



}

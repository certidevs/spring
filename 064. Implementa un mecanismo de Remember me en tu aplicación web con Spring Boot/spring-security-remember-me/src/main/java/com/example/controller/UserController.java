package com.example.controller;

import com.example.model.UserEntity;
import com.example.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserEntityRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public String index(){
        return "index";
    }

    // iniciar sesión
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // mostrar formulario de registro
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserEntity());
        return "signup-form";
    }

    // procesar formulario de registro
    @PostMapping("/process-register")
    public String processRegister(UserEntity user){

        repo.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new RuntimeException("Username already exists");
        });

        repo.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already exists");
        });

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "register-success";
    }

    // mostrar usuarios
    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("listUsers", repo.findAll());
        return "users";
    }

}

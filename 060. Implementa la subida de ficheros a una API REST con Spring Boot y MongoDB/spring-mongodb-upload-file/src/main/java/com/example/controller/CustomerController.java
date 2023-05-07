package com.example.controller;

import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // http://localhost:8080/customers/5f1f1f1f1f1f1f1f1f1f1f1f
    @GetMapping("/{id}")
    public String findById(@PathVariable String id, Model model){
        var customer = service.findById(id);
        model.addAttribute("id", customer.getId());
        model.addAttribute("email", customer.getEmail());

        String avatarBase64 = Base64.getEncoder()
                .encodeToString(customer.getAvatar().getData());

        model.addAttribute("avatar", avatarBase64);
        return "customer";
    }

    @PostMapping
    public String create(@RequestParam("email") String email,
                         @RequestParam("avatar") MultipartFile avatar) throws IOException {

        var customer = new Customer(null, email, null);
        String id = service.save(customer, avatar);
        return "redirect:/customers/" + id; // redirect hacia findById
    }


}

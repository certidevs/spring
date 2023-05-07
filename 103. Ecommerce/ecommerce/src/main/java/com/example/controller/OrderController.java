package com.example.controller;

import com.example.model.Order;
import com.example.model.UserEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/orders")
    public String findAll(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderRepo.findAllByUserUsername(username);
        model.addAttribute("orders", orders);
        return "order_list";
    }

    @GetMapping("/orders/{id}")
    public String findById(Model model, @PathVariable Long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Order order = orderRepo.findByIdAndUserUsername(id, username).orElseThrow();
        model.addAttribute("order", order);
        model.addAttribute("products", productRepo.findAllByOrderId(id));
        return "order_detail";
    }


}

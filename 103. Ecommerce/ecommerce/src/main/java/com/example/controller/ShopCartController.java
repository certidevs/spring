package com.example.controller;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.UserEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserEntityRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ShopCartController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserEntityRepository userRepo;

    @Autowired
    private HttpSession session;

    @GetMapping("/shopcart")
    public String show(Model model){
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
                .orElse(new ArrayList<>());

        model.addAttribute("products", productRepo.findAllById(productIds));
        return "shopcart";
    }

    @GetMapping("/shopcart/add/{id}")
    public String add(@PathVariable Long id){

        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
                .orElse(new ArrayList<>());

        if(!productIds.contains(id)) productIds.add(id);

        session.setAttribute("shopcart_products", productIds);
        return "redirect:/shopcart";
    }

    @GetMapping("/shopcart/remove/{id}")
    public String remove(@PathVariable Long id){

        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
                .orElse(new ArrayList<>());

        productIds.removeIf(productId -> productId.equals(id));

        session.setAttribute("shopcart_products", productIds);
        return "redirect:/shopcart";
    }

    @GetMapping("/shopcart/checkout")
    public String checkout(){
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
                .orElse(new ArrayList<>());
        List<Product> products = productRepo.findAllById(productIds);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByUsername(username).orElseThrow();

        Order order = new Order(LocalDateTime.now(), user);
        orderRepo.save(order);

        products.forEach(p -> p.setOrder(order));
        productRepo.saveAll(products);

        session.removeAttribute("shopcart_products");

        return "redirect:/orders/" + order.getId();

    }

    @ModelAttribute("shopcart_price")
    public Double calculateTotal(){
        List<Long> productIds = Optional.ofNullable((List<Long>) session.getAttribute("shopcart_products"))
                .orElse(new ArrayList<>());
        List<Product> products = productRepo.findAllById(productIds);

        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}

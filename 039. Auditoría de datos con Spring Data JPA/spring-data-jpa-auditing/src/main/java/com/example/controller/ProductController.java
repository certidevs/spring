package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return this.productRepository.save(product);
    }
}

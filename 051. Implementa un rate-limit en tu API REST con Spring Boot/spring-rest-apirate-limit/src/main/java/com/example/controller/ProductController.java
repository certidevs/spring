package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/products")
public class ProductController {

//    private final Bucket bucket;

    @Autowired
    private ProductRepository repo;

    public ProductController() {

    }

    @GetMapping
    public List<Product> findAll(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> save(Product product){

            return ResponseEntity.ok(repo.save(product));

    }

    @GetMapping("/premium")
    public List<Product> findAllPremium(){
        return repo.findAll();
    }


}

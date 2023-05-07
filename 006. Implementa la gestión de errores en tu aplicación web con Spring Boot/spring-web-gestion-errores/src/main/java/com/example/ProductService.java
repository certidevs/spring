package com.example;

public interface ProductService {


    Product findById(Long id);
    Product save(Product product);
    Product delete(Product product);
}

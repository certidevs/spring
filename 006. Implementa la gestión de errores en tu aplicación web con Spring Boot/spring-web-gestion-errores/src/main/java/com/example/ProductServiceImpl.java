package com.example;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product findById(Long id) {
        throw new NoSuchElementException("Product with id {" + id + "} not found");
    }

    @Override
    public Product save(Product product) {
        throw new ProductTitleException(product);
    }

    @Override
    public Product delete(Product product) {
        throw new NullPointerException();
    }
}

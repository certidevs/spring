package com.example.service;

import com.example.error.ProductTitleException;
import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product findById(Long id) {
        throw new NoSuchElementException("Product not found");
    }

    @Override
    public Product save(Product product) {
        throw new ProductTitleException(product);
    }

    @Override
    public Product deleteById(Long id) {
       throw new NullPointerException("Error al intentar borrar producto");
    }
}

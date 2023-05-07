package com.example.error;

import com.example.model.Product;

public class ProductTitleException extends RuntimeException {
    private Product product;
    public ProductTitleException(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}

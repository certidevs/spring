package com.example.model;

public record Customer(Long id, String name, Boolean active, String category, Double bonus) {
    public Customer(Long id, String name, Boolean active, String category) {
        this(id, name, active, category, null);
    }
}

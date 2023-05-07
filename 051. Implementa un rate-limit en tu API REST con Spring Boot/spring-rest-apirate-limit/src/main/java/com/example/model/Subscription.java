package com.example.model;

import javax.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double price;

    private Long capacity;

    private Long refillMinutes;

    @OneToOne(mappedBy = "subscription")
    private UserEntity user;

    public Subscription() {
    }

    public Subscription(Long id, String title, Double price, Long capacity, Long refillMinutes) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.capacity = capacity;
        this.refillMinutes = refillMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getRefillMinutes() {
        return refillMinutes;
    }

    public void setRefillMinutes(Long refillMinutes) {
        this.refillMinutes = refillMinutes;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", refillMinutes=" + refillMinutes +
                '}';
    }
}

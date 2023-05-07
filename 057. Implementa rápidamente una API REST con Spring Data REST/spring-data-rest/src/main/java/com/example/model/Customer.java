package com.example.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String dept;

    private String category;

    @OneToOne
    @JoinColumn(name="address_id")
    @RestResource(path = "customer-address", rel="address")
    private Address address;

    public Customer() {
    }

    public Customer(Long id, String email, String dept, String category, Address address) {
        this.id = id;
        this.email = email;
        this.dept = dept;
        this.category = category;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", category='" + category + '\'' +
                ", address=" + address +
                '}';
    }
}

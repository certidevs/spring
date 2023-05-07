package com.example.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import static com.example.model.CustomerView.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CustomerUser.class)
    private Long id;

    @JsonView(CustomerUser.class)
    private String name;

    @JsonView(CustomerUser.class)
    private String email;

    @JsonView(CustomerUser.class)
    private Integer age;

    @JsonView(CustomerManager.class)
    private Double salary;

    @JsonView(CustomerManager.class)
    private Boolean married;

    @JsonView(CustomerAdmin.class)
    private String password;

    @JsonView(CustomerAdmin.class)
    private String creditCard;

    @JsonView(CustomerManager.class)
    private LocalDateTime created;

    public Customer() {
    }

    public Customer(String name, String email, Double salary, Integer age, Boolean married, String password, String creditCard) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.age = age;
        this.married = married;
        this.password = password;
        this.creditCard = creditCard;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", married=" + married +
                ", password='" + password + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", created=" + created +
                '}';
    }
}

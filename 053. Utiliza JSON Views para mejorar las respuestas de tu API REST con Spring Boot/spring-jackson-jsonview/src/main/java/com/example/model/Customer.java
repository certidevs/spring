package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;

import static com.example.model.CustomerView.*;

/*
Se utiliza @JsonView cuando una entidad/modelo es complejo
y se quiere mostrar solo una parte de sus atributos en distintas vistas
 */
public class Customer {

    @JsonView({CustomerList.class, CustomerFull.class})
    private Long id;

    @JsonView({CustomerDetail.class, CustomerFull.class})
    private String name;

    @JsonView({CustomerList.class, CustomerEdit.class, CustomerFull.class})
    private String email;

    @JsonView({CustomerDetail.class, CustomerEdit.class, CustomerFull.class})
    private Double salary;

    @JsonIgnore
    private String password;

    @JsonView({CustomerEdit.class, CustomerFull.class})
    private String creditCard;

    @JsonView({CustomerFull.class})
    private LocalDateTime createdAt;

    @JsonView({CustomerFull.class})
    private LocalDateTime lastLogin;

    @JsonView({CustomerDetail.class, CustomerEdit.class, CustomerFull.class})
    private Integer year;

    @JsonView({CustomerDetail.class, CustomerEdit.class, CustomerFull.class})
    private Boolean married;


    public Customer() {
    }

    public Customer(Long id, String name, String email, Double salary, String password, String creditCard, Integer year, Boolean married) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.password = password;
        this.creditCard = creditCard;
        this.createdAt = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
        this.year = year;
        this.married = married;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                ", year=" + year +
                ", married=" + married +
                '}';
    }
}

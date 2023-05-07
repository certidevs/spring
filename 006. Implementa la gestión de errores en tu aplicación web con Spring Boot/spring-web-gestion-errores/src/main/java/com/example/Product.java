package com.example;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Product {

    private Long id;

    @Size(min = 10, max = 100)
    @NotNull(message = "Título producto no puede ser nulo")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Fecha producto no puede ser nula")
    private LocalDate released;

    @Min(value = 5)
    @NotNull(message = "Precio producto no puede ser nulo")
    private Double price;

    public Product() {
    }

    public Product(Long id, String title, LocalDate released, Double price) {
        this.id = id;
        this.title = title;
        this.released = released;
        this.price = price;
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

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", released=" + released +
                ", price=" + price +
                '}';
    }
}

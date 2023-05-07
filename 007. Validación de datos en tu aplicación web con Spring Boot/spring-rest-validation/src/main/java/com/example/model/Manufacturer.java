package com.example.model;

import jakarta.validation.constraints.NotNull;

public class Manufacturer {

    @NotNull(message = "Nombre obligatorio")
    private String name;
    private Integer year;

    public Manufacturer() {
    }

    public Manufacturer(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}

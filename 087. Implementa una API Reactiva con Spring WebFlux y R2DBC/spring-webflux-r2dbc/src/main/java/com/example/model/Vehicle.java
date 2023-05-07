package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("vehicles")
public class Vehicle {

    @Id
    private Long id;

    private String model;

    @Column("cubic_centimeters")
    private Double cubicCentimeters;

    private Integer released;

    public Vehicle() {
    }

    public Vehicle(Long id, String model, Double cubicCentimeters, Integer released) {
        this.id = id;
        this.model = model;
        this.cubicCentimeters = cubicCentimeters;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getCubicCentimeters() {
        return cubicCentimeters;
    }

    public void setCubicCentimeters(Double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", cubicCentimeters=" + cubicCentimeters +
                ", released=" + released +
                '}';
    }
}

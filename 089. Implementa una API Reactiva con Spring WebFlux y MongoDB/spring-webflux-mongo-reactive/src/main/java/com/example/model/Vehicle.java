package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "vehicles")
public class Vehicle {

    @Id
    private String id;

    private String model;

    @Field(name = "cc")
    private Double cubicCentimeters;

    private Integer released;

    public Vehicle() {
    }

    public Vehicle(String id, String model, Double cubicCentimeters, Integer released) {
        this.id = id;
        this.model = model;
        this.cubicCentimeters = cubicCentimeters;
        this.released = released;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", cubicCentimeters=" + cubicCentimeters +
                ", released=" + released +
                '}';
    }
}

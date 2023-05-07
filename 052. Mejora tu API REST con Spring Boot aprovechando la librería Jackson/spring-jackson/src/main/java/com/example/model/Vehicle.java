package com.example.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@JsonPropertyOrder({"id", "cc", "releasedYear", "model"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

//    @JsonProperty("cc")
    private Double cubicCentimeters;

    private Integer releasedYear;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Vehicle() {}

    public Vehicle(String model, Double cubicCentimeters, Integer releasedYear, Customer customer) {
        this.model = model;
        this.cubicCentimeters = cubicCentimeters;
        this.releasedYear = releasedYear;
        this.customer = customer;
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
    @JsonProperty("cc")
    public Double getCubicCentimeters() {
        return cubicCentimeters;
    }
    @JsonProperty("cubicCentimeters")
    public void setCubicCentimeters(Double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", cubicCentimeters=" + cubicCentimeters +
                ", releasedYear=" + releasedYear +
                '}';
    }
}

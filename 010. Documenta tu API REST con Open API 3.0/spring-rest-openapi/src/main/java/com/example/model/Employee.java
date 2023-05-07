package com.example.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class Employee {

    @Schema(example = "4", description = "Identificador clave primaria empleado numérico")
    private Long id;

    @Schema(example = "Alan", description = "Nombre completo empleado")
    private String name;

    @Schema(example = "2022-01-01", description = "Fecha de nacimiento yyyy-MM-dd")
    private LocalDate birthDate;

    @Schema(example = "34567.43", description = "Salario anual bruto del empleado")
    private Double salary;

    @Schema(example = "true", description = "Situación casado si o no empleado")
    private Boolean married;

    public Employee() {
    }

    public Employee(Long id, String name, LocalDate birthDate, Double salary, Boolean married) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.salary = salary;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", married=" + married +
                '}';
    }
}

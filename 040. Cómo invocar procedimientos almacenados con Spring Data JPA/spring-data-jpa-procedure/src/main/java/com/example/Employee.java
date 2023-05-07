package com.example;

import javax.persistence.*;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "employee_move_to_history",
                procedureName = "employee_move_to_history",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "employee_id_in", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "status_out", type = Boolean.class)
                }
        )
})
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Double salary;

    public Employee() {
    }

    public Employee(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Employee(Long id, String email, Double salary) {
        this.id = id;
        this.email = email;
        this.salary = salary;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}

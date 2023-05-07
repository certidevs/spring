package com.example.example2;

import javax.persistence.Entity;

@Entity
public class Employee extends BaseUser{

    private Integer experienceYears;

    public Employee() {

    }

    public Employee(Long id, String email, Integer experienceYears) {
        super(id, email);
        this.experienceYears = experienceYears;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "experienceYears=" + experienceYears +
                '}';
    }
}

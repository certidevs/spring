package com.example.example2;


import javax.persistence.Entity;

@Entity
public class Person extends BaseUser{

    private Boolean married;

    public Person(Long id, String email, Boolean married) {
        super(id, email);
        this.married = married;
    }

    public Person() {

    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Person{" +
                "married=" + married +
                '}';
    }
}

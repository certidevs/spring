package com.example;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer", indexes = {
        @Index(name = "idx_customer_type", columnList = "type"),
        @Index(name = "idx_customer_full_name_title", columnList = "full_name, title")
})
@NamedQueries({
        @NamedQuery(name = "Customer.countByTitleLikeIgnoreCase", query = "select count(c) from Customer c where upper(c.title) like upper(?1)")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ElementCollection
    @Column(name = "phone")
    @CollectionTable(name = "customer_phones", joinColumns = @JoinColumn(name = "customer_id"))
    private Set<String> phones = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Transient
    private String isEligible;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "title")
    private String title;

    public Customer() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getVersion() {
        return version;
    }

    public String getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(String isEligible) {
        this.isEligible = isEligible;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PreRemove
    public void preRemove() {
        System.out.println("preRemove");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fullName = " + fullName + ", " +
                "type = " + type + ", " +
                "isEligible = " + isEligible + ", " +
                "version = " + version + ", " +
                "title = " + title + ")";
    }
}
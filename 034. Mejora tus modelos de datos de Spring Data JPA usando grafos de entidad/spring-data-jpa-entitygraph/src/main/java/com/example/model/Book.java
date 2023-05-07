package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraph(name = "book-categories", attributeNodes = @NamedAttributeNode("categories"))
@NamedEntityGraph(name = "book-ratings", attributeNodes = @NamedAttributeNode("ratings"))
@NamedEntityGraph(name = "book-all", attributeNodes = {@NamedAttributeNode("ratings"),@NamedAttributeNode("categories")})

@NamedEntityGraph(name = "book-all-with-ratings-users",
        attributeNodes = {
        @NamedAttributeNode(value = "ratings", subgraph = "ratings-users"),
        @NamedAttributeNode("categories")
},
subgraphs = {
        @NamedSubgraph(name = "ratings-users", attributeNodes = {
                @NamedAttributeNode("user")
        })
})

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @ManyToMany()
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Rating> ratings = new HashSet<>();

    public Book() {
    }

    public Book(Long id, String title, String author, Set<Category> categories, Set<Rating> ratings) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.ratings = ratings;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

package com.example.repository;

import com.example.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Hibernate 5
    // @Query("SELECT distinct a FROM Author a inner join fetch a.books")
    
    // Hibernate 6
    @Query("SELECT a FROM Author a inner join fetch a.books")
    List<Author> findWithBooks();
}
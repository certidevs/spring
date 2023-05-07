package com.example.repository;

import com.example.dao.BookDAO;
import com.example.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookDAO {

    @EntityGraph(attributePaths = {"categories"})
    List<Book> findAllByAuthor(String author);

    @EntityGraph(attributePaths = {"ratings"})
    List<Book> findFirst10ByAuthor(String author);

    @EntityGraph(attributePaths = {"ratings", "categories"})
    List<Book> findFirst15ByAuthor(String author);

    @EntityGraph(value = "book-all")
    List<Book> findFirst20ByAuthor(String author);

    @EntityGraph(value = "book-all-with-ratings-users")
    List<Book> findFirst25ByAuthor(String author);
}

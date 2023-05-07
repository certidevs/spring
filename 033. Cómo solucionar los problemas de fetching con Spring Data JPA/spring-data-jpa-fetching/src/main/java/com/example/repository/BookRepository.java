package com.example.repository;

import com.example.model.Book;
import com.example.projection.BookTitle;
import com.example.projection.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    /*
    Otro enfoque sería utilizar la API Entity Graph
     */
    @Query("SELECT b FROM Book b join fetch b.author a")
    List<Book> findAllWithAuthor();

    @Query("SELECT b FROM Book b join fetch b.author a WHERE a.name = :name")
    List<Book> findAllWithAuthorByName(String name);

    @Query("SELECT b FROM Book b join fetch b.author a join fetch a.address ad")
    List<Book> findAllWithAuthorAndAddress();

    List<BookTitle> findTitleByAuthorName(String name);

    @Query("""
            SELECT
            b.title as bookTitle,
            a.name as authorName,
            ad.city as authorCity
            FROM Book b
            JOIN b.author a
            JOIN a.address ad
            WHERE b.title like :title
            """)
    List<BookView> findViewByTitleLike(String title);


}
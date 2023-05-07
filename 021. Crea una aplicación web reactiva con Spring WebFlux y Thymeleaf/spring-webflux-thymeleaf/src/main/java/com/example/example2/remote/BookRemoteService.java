package com.example.example2.remote;

import com.example.example2.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Suponemos que esta clase es un servicio de otra aplicación
 */
@RestController
public class BookRemoteService {

    @GetMapping("/remote/books")
    public List<Book> findAll(){
        List<Book> books = List.of(
                new Book(1L, "book1", "author1"),
                new Book(2L, "book2", "author2"),
                new Book(3L, "book3", "author3"),
                new Book(4L, "book4", "author4")
        );
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}

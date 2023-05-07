package com.example;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static com.example.model.QAuthor.author;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var bookRepo = ctx.getBean(BookRepository.class);
        var authorRepo = ctx.getBean(AuthorRepository.class);

        var author1 = new Author(null, "Author 1", "dep1", "cat1", 2000.0, LocalDate.of(2000, 1, 1), true, null);
        var author2 = new Author(null, "Author 2", "dep2", "cat1", 2000.0, LocalDate.of(2001, 1, 1), true, null);
        var author3 = new Author(null, "Author 2", "dep3", "cat1", 2000.0, LocalDate.of(2000, 1, 1), true, null);
        authorRepo.saveAll(List.of(author1, author2, author3));

        var book1 = new Book(null, "123", "book1", author1);
        var book2 = new Book(null, "123", "book1", author1);
        var book3 = new Book(null, "123", "book1", author1);
        bookRepo.saveAll(List.of(book1, book2, book3));


    }

}

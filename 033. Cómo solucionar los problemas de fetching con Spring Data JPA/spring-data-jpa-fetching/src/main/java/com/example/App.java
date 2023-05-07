package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AddressRepository;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var bookRepo = ctx.getBean(BookRepository.class);
        var authorRepo = ctx.getBean(AuthorRepository.class);
        var addressRepo = ctx.getBean(AddressRepository.class);

        Address add1 = new Address(null, "calle1", "city1");
        Address add2 = new Address(null, "calle2", "city2");
        Address add3 = new Address(null, "calle3", "city3");
        Address add4 = new Address(null, "calle4", "city4");
        addressRepo.saveAll(List.of(add1, add2, add3, add4));

        Author author1 = new Author("author1", add1);
        Author author2 = new Author("author2", add2);
        Author author3 = new Author("author3", add3);
        Author author4 = new Author("author4", add4);
        authorRepo.saveAll(List.of(author1, author2, author3, author4));

        Book book1 = new Book(null, "book1", "editorial1", author1);
        Book book2 = new Book(null, "book2", "editorial1", author1);
        Book book3 = new Book(null, "book3", "editorial1", author2);
        Book book4 = new Book(null, "book4", "editorial1", author2);
        Book book5 = new Book(null, "book5", "editorial1", author2);
        Book book6 = new Book(null, "book6", "editorial1", author3);
        Book book7 = new Book(null, "book7", "editorial1", author3);
        Book book8 = new Book(null, "book8", "editorial1", null);
        bookRepo.saveAll(List.of(book1, book2, book3, book4, book5, book6, book7, book8));

//        bookRepo.findAll().forEach(System.out::println);
//
//        bookRepo.findAll().forEach(book -> {
//            System.out.println(book.getTitle());
////            System.out.println(book.getAuthor().getName());
//        });
//
//        bookRepo.findAllWithAuthor().forEach(book -> {
//            System.out.println(book.getAuthor().getName());
//        });
//
//        bookRepo.findAllWithAuthorByName("author2").forEach(book -> {
//            System.out.println(book.getAuthor().getName());
////            System.out.println(book.getAuthor().getAddress().getCity());
//        });
//
//        bookRepo.findAllWithAuthorAndAddress().forEach(book -> {
//            System.out.println(book.getAuthor().getAddress().getCity());
//        });
//
//        authorRepo.findById(2L).ifPresent(author -> {
//            System.out.println(author.getName());
////            System.out.println(author.getBooks().size());
//        });
//
//        authorRepo.findWithBooks().forEach(author -> {
//            System.out.println(author.getName());
//            System.out.println(author.getBooks().size());
//        });
//
//        bookRepo.findTitleByAuthorName("author2").forEach(book -> {
//            System.out.println(book.getTitle());
//        });

        bookRepo.findViewByTitleLike("book%").forEach(book -> {
            System.out.println(book.getBookTitle());
            System.out.println(book.getAuthorName());
            System.out.println(book.getAuthorCity());
        });
    }

}

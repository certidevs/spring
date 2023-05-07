package com.example;

import com.example.dao.BookDAO;
import com.example.model.Book;
import com.example.model.QBook;
import com.example.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        var repo = ctx.getBean(BookRepository.class);
        var dao = ctx.getBean(BookDAO.class);

        var book1 = new Book(null, "book1", "author1", 20.1, 2020);
        var book2 = new Book(null, "book2", "author2", 20.1, 2020);
        var book3 = new Book(null, "book3", "author3", 20.1, 2020);
        repo.saveAll(List.of(book1, book2, book3));

//        repo.findByTitleContainsDsl("Java").forEach(System.out::println);
//        repo.findByTitleContains("Java").forEach(System.out::println);

        var pred = QBook.book.title.startsWith("book")
                .and(QBook.book.title.endsWith("2"))
                .and(QBook.book.price.between(10, 25))
                .and(QBook.book.releasedYear.goe(2019));

        Pageable pagination = PageRequest.of(0, 5);
        repo.findAll(pred, pagination).forEach(System.out::println);
//        dao.findByTitleContains("Java").forEach(System.out::println);
    }

}

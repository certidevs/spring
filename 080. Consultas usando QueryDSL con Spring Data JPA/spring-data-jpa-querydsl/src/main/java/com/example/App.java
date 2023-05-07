package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.QAuthor;
import com.example.repository.AddressRepository;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var bookRepo = ctx.getBean(BookRepository.class);
        var addressRepo = ctx.getBean(AddressRepository.class);
        var authorRepo = ctx.getBean(AuthorRepository.class);

        var add1 = new Address(null, "city1", "street1");
        var add2 = new Address(null, "city2", "street2");
        var add3 = new Address(null, "city3", "street3");
        var add4 = new Address(null, "city4", "street4");
        addressRepo.saveAll(List.of(add1, add2, add3, add4));

        var auth1 = new Author("auth1", "dep1", "cat1", 1000, LocalDate.of(2020,1,1), true, add1);
        var auth2 = new Author("auth2", "dep2", "cat1", 2000, LocalDate.of(2021,1,1), false, add2);
        var auth3 = new Author("auth3", "dep1", "cat2", 3000, LocalDate.of(2022,1,1), true, add3);
        var auth4 = new Author("auth4", "dep2", "cat2", 4000, LocalDate.of(2023,1,1), false, add4);
        authorRepo.saveAll(List.of(auth1, auth2, auth3, auth4));


        var book1 = new Book(null, "book1", "1234", auth1);
        var book2 = new Book(null, "book2", "1234", auth1);
        var book3 = new Book(null, "book3", "1234", auth2);
        var book4 = new Book(null, "book4 drama", "1234", auth2);
        var book5 = new Book(null, "book5", "1234", auth3);
        bookRepo.saveAll(List.of(book1, book2, book3, book4, book5));

        BooleanExpression pred = QAuthor.author.name.startsWith("auth")
                .and(QAuthor.author.salary.goe(2000))
                .and(QAuthor.author.dept.eq("dep1").or(QAuthor.author.dept.eq("dep2")))
                .and(QAuthor.author.category.in("cat1", "cat2"))
                .and(QAuthor.author.married.isFalse())
                .and(QAuthor.author.birth.between(LocalDate.of(2021,1,1), LocalDate.of(2022,1,2)))
                .and(QAuthor.author.address.city.eq("city1").or(QAuthor.author.address.city.eq("city2")))
                .and(QAuthor.author.books.any().title.contains("drama"));

        OrderSpecifier[] orders = {QAuthor.author.salary.desc(), QAuthor.author.name.asc()};
        authorRepo.findAll(pred, orders).forEach(System.out::println);


    }

}

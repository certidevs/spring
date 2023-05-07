package com.example.example3.controller;

import com.example.example3.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;


@Controller
public class BookReactiveController {

    @GetMapping("/books/reactive")
    public String findAll(Model model){

        WebClient client = WebClient.create("http://localhost:8080/remote/books/reactive");
        Flux<Book> books1 = client.get().retrieve().bodyToFlux(Book.class);
        Flux<Book> books2 = client.get().retrieve().bodyToFlux(Book.class);
        Flux<Book> books3 = client.get().retrieve().bodyToFlux(Book.class);
        Flux<Book> books = Flux.merge(books1, books2, books3);

        model.addAttribute("books", new ReactiveDataDriverContextVariable(books, 1));
        return "book-list";
    }
}

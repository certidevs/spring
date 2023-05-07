package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.print.attribute.standard.Media;
import java.net.URI;

@Configuration
public class BookFunctionalController {

    @Autowired
    private BookService bookService;

    @Bean
    public RouterFunction<ServerResponse> findAllBooks(){
        return RouterFunctions.route().GET(
                "/functional/books",
                request -> ServerResponse.ok().body(bookService.findAll(), Book.class)
        ).build();
    }

    @Bean
    public RouterFunction<ServerResponse> findById(){
        return RouterFunctions.route().GET(
                "/functional/books/{id}",
                request -> ServerResponse.ok().body(
                        bookService.findById(Long.valueOf(request.pathVariable("id"))),
                        Book.class
                )
        ).build();
    }

    @Bean
    public RouterFunction<ServerResponse> createBook(){
        return RouterFunctions.route().POST(
                "/functional/books",
                request -> request.bodyToMono(Book.class)
                        .flatMap(bookService::create)
                        .flatMap(book ->
                                ServerResponse.created(URI.create("/functional/books/" + book.getId()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(book))
                        )
        ).build();
    }

}

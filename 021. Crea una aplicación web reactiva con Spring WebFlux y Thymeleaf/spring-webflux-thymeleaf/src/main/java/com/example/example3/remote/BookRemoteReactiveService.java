package com.example.example3.remote;

import com.example.example3.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
Suponemos que esta clase es un servicio de otra aplicación
 */
@RestController
public class BookRemoteReactiveService {

    @GetMapping("/remote/books/reactive")
    public Flux<Book> findAll(){
        return Flux.just(
                new Book(1L, "book1", "author1"),
                new Book(2L, "book2", "author2"),
                new Book(3L, "book3", "author3"),
                new Book(4L, "book4", "author4")
        ).delaySequence(Duration.ofSeconds(2));
    }
}

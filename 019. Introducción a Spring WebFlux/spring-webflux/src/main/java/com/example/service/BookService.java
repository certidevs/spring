package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Mono<Book> findById(Long id){
        return this.repository.findById(id);
    }

    public Flux<Book> findAll(){
        return this.repository.findAll();
    }

    public Mono<Book> create(Book book){
        if(book.getId() != null)
            return Mono.error(new IllegalArgumentException("Id no debe ser nulo"));

        return this.repository.save(book);
    }

    public Mono<Book> update(Book book){
        if(book.getId() == null)
            return Mono.error(new IllegalArgumentException("Id debe ser nulo"));

        return this.repository.existById(book.getId()).flatMap(exist -> exist ?
                this.repository.save(book) :
                Mono.error(new IllegalArgumentException("Book debe existir")));
    }

    public Mono<Void> deleteById(Long id){
        return this.repository.deleteById(id);
    }

    public Mono<Void> deleteAll(){
        return this.repository.deleteAll();
    }

}

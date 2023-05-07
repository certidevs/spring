package com.example.service

import com.example.domain.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI

@Service
class BookHandler(@Autowired val bookService: BookService) {

    fun findAll(request: ServerRequest) =
        ServerResponse.ok()
            .body(bookService.findAll(), Book::class.java)
            .switchIfEmpty(ServerResponse.notFound().build())


    fun findById(request: ServerRequest) =
        bookService.findById(request.pathVariable("id").toLong())
            .flatMap { book -> ServerResponse.ok().body(Mono.just(book), Book::class.java) }
            .switchIfEmpty(ServerResponse.notFound().build())

    fun create(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
            .flatMap { bookService.create(it) }
            .flatMap { book -> ServerResponse.created(URI.create("/api/books/${book.id}"))
                .body(BodyInserters.fromValue(book)) }

    fun update(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
            .flatMap { bookService.update(it) }
            .flatMap { book -> ServerResponse.ok().body(BodyInserters.fromValue(book)) }

    fun deleteById(request: ServerRequest) =
        bookService.findById(request.pathVariable("id").toLong())
            .flatMap { ServerResponse.noContent().build(bookService.deleteById(it.id!!)) }
            .switchIfEmpty(ServerResponse.notFound().build())

    fun deleteAll(request: ServerRequest) =
        ServerResponse.noContent().build(bookService.deleteAll())
}
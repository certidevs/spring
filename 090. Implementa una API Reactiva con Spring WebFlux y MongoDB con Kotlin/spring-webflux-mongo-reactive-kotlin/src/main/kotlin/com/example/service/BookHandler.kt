package com.example.service

import com.example.model.Book
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class BookHandler(private val service: BookService) {

    fun findAll(request: ServerRequest) = ServerResponse.ok().body(service.findAll(), Book::class.java)

    fun findAllByAuthor(request: ServerRequest) =
        ServerResponse.ok().body(
            service.findAllByAuthor(request.pathVariable("author")),
            Book::class.java
        )

    fun findById(request: ServerRequest) =
        service.findById(request.pathVariable("id"))
        .flatMap { book -> ServerResponse.ok().body(Mono.just(book), Book::class.java) }
        .switchIfEmpty(ServerResponse.notFound().build())

    fun create(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
        .flatMap(service::create)
        .flatMap { book -> ServerResponse
                            .created(URI.create("/api/books/${book.id}"))
                            .body(BodyInserters.fromValue(book))
        }

    fun update(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
            .flatMap(service::update)
            .flatMap { book -> ServerResponse.ok().body(BodyInserters.fromValue(book)) }

    fun deleteById(request: ServerRequest) =
        service.findById(request.pathVariable("id"))
            .flatMap { book -> ServerResponse.noContent().build(service.deleteById(book.id!!)) }
            .switchIfEmpty(ServerResponse.notFound().build())

}
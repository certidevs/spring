package com.example.service

import com.example.domain.Book
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class BookHandler(private val service: BookService) {

    fun findAll(request: ServerRequest) =
        ServerResponse.ok().body(service.findAll(), Book::class.java)
            .switchIfEmpty { ServerResponse.notFound().build() }

    fun findById(request: ServerRequest) = service.findById(request.pathVariable("id").toLong())
        .flatMap { ServerResponse.ok().bodyValue(it) }
        .switchIfEmpty { ServerResponse.notFound().build() }

    fun findAllByAuthor(request: ServerRequest) =
        ServerResponse.ok().body(service.findAllByAuthor(request.pathVariable("author")), Book::class.java)
            .switchIfEmpty { ServerResponse.notFound().build() }

    fun create(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
            .flatMap { ServerResponse.ok().body(service.create(it), Book::class.java) }
            .switchIfEmpty { ServerResponse.badRequest().build() }

    fun update(request: ServerRequest) =
        request.bodyToMono(Book::class.java)
            .flatMap { ServerResponse.ok().body(service.update(it), Book::class.java) }
            .switchIfEmpty { ServerResponse.badRequest().build() }

    fun deleteById(request: ServerRequest) =
        service.deleteById(request.pathVariable("id").toLong())
        .flatMap { ServerResponse.noContent().build() }
        .switchIfEmpty { ServerResponse.notFound().build() }


}
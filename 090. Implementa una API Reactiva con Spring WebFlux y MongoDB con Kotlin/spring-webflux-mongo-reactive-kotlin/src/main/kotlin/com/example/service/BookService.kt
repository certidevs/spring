package com.example.service

import com.example.model.Book
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BookService {
    fun findAll(): Flux<Book>
    fun findAllByAuthor(author: String): Flux<Book>
    fun findById(id: String): Mono<Book>

    fun create(book:Book): Mono<Book>
    fun update(book:Book): Mono<Book>

    fun deleteById(id: String): Mono<Void>
    fun deleteAll(): Mono<Void>


}
package com.example.repository

import com.example.domain.Book
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux

interface BookRepository : R2dbcRepository<Book, Long> {

    @Query("SELECT * FROM books WHERE author = :author")
    fun findAllByAuthor(author: String): Flux<Book>
}
package com.example.repository

import com.example.domain.Book
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class BookRepository {

    private var database = mutableMapOf(
        1L to Book(1L, "Book1", "author 1"),
        2L to Book(2L, "Book2", "author 2"),
        3L to Book(3L, "Book3", "author 3"),
        4L to Book(4L, "Book4", "author 4"),
        5L to Book(5L, "Book5", "author 5"),
        6L to Book(6L, "Book6", "author 6"),
        7L to Book(7L, "Book7", "author 7"),
        8L to Book(8L, "Book8", "author 8"),
        9L to Book(9L, "Book9", "author 9"),
    )

    fun findById(id: Long): Mono<Book> = Mono.just(database[id]!!)

    fun findAll(): Flux<Book> = Flux.fromIterable(database.values)

    fun existById(id: Long): Mono<Boolean>{
        return if (database.containsKey(id)) Mono.just(true) else Mono.just(false)
    }

    fun save(book: Book): Mono<Book>{
        book.id = book.id ?: (database.values.mapNotNull { it.id }.maxOf { it.or(0L) } + 1)
        database[book.id!!] = book
        return Mono.just(book)
    }

    fun deleteById(id: Long): Mono<Void>{
        database.remove(id)
        return Mono.empty()
    }

    fun deleteAll(): Mono<Void>{
        database.clear()
        return Mono.empty()
    }

}
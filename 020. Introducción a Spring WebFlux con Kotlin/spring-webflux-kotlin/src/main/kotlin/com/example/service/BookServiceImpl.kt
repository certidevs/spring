package com.example.service

import com.example.domain.Book
import com.example.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookServiceImpl(@Autowired val bookRepository: BookRepository) : BookService {

    private fun upperText(book: Book): Book {
        book.title = book.title.uppercase()
        book.author = book.author.uppercase()
        return book
    }

    override fun findById(id: Long): Mono<Book> {
        return bookRepository.findById(id).map(::upperText)
    }

    override fun findAll(): Flux<Book> {
        return bookRepository.findAll().map(::upperText)
    }

    override fun create(book: Book): Mono<Book> {
        return if (book.id != null) Mono.error(IllegalArgumentException("Book id must be null"))
        else bookRepository.save(book)
    }

    override fun update(book: Book): Mono<Book> {
        return bookRepository.existById(book.id!!)
            .flatMap { exist ->
                if (exist) return@flatMap bookRepository.save(book)
                else return@flatMap Mono.error(IllegalArgumentException("Book must exist"))
            }
    }

    override fun deleteById(id: Long): Mono<Void> {
        return bookRepository.deleteById(id)
    }

    override fun deleteAll(): Mono<Void> {
        return bookRepository.deleteAll()
    }
}
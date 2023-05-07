package com.example.service

import com.example.domain.Book
import com.example.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookServiceImpl(@Autowired val repo: BookRepository) : BookService {


    private fun upperText(book: Book): Book {
        book.title = book.title.uppercase()
        return book
    }

    override fun findAll(): Flux<Book> {
//        return repo.findAll().map { book -> upperText(book) }
//        return repo.findAll().map(::upperText)
        return repo.findAll().map { upperText(it) }

    }

    override fun findAllByAuthor(author: String): Flux<Book> {
        return repo.findAllByAuthor(author).map { upperText(it) }    }

    override fun findById(id: Long): Mono<Book> {
        return repo.findById(id).map { upperText(it) }
    }

    override fun create(book: Book): Mono<Book> {
        return if(book.id != null) Mono.error(IllegalArgumentException("Book id must be null"))
        else repo.save(book)
    }

    override fun update(book: Book): Mono<Book> {
        return repo.existsById(book.id!!).flatMap { exists ->
            if(exists) repo.save(book)
            else Mono.error(IllegalArgumentException("Book id must be not null and book must exists"))
        }
    }

    override fun deleteById(id: Long): Mono<Void> {
        return repo.deleteById(id)
    }

    override fun deleteAll(): Mono<Void> {
        return repo.deleteAll()
    }
}
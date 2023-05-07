package com.example.service

import com.example.model.Book
import com.example.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookServiceImpl(@Autowired val repo: BookRepository) : BookService {

    override fun findAll(): Flux<Book> = repo.findAll()

//    override fun findAllByAuthor(author: String): Flux<Book> = repo.findAll().filter { it.author == author }
    override fun findAllByAuthor(author: String): Flux<Book> = repo.findAllByAuthor(author)

    override fun findById(id: String): Mono<Book> = repo.findById(id)

    override fun create(book: Book): Mono<Book> = repo.save(book)

    override fun update(book: Book): Mono<Book> {
        return repo.existsById(book.id!!).flatMap { exists ->
            if (exists) {
                return@flatMap repo.save(book)
            } else {
                return@flatMap Mono.error<Book>(IllegalArgumentException("Book id mandatory"))
            }
        }
    }

    override fun deleteById(id: String): Mono<Void> = repo.deleteById(id)

    override fun deleteAll(): Mono<Void> = repo.deleteAll()
}
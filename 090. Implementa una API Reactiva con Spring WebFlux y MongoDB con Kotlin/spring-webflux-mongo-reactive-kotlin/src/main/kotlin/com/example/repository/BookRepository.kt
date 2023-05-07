package com.example.repository

import com.example.model.Book
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BookRepository: ReactiveMongoRepository<Book, String> {

    @Query("{ 'author' : ?0 }")
    fun findAllByAuthor(author: String): Flux<Book>
}

//class BookRepository(private val template: ReactiveMongoTemplate){
//    fun findAll() = template.findAll(Book::class.java)
//    fun findById(id: String) = template.findById(id, Book::class.java)
//}
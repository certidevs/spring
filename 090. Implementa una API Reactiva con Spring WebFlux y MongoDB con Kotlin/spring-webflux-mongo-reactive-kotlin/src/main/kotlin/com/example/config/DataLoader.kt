package com.example.config

import com.example.model.Book
import com.example.repository.BookRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Component

@Component
class DataLoader {

    @Bean
    fun runner(repo: BookRepository, mongo: ReactiveMongoTemplate) = ApplicationRunner {
        val deleteAll = mongo.dropCollection(Book::class.java)

        val saveAll = repo.saveAll(listOf(
                Book(null, "Book 1", "Author 1"),
                Book(null, "Book 2", "Author 2"),
                Book(null, "Book 3", "Author 3")
        ))

        deleteAll.thenMany(saveAll).thenMany(repo.findAll()).subscribe { println(it) }
    }


}
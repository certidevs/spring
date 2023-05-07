package com.example.controller

import com.example.service.BookHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class BookController {

    @Bean
    fun mainRouter(handler: BookHandler) = router {
        ("/api/books" and accept(MediaType.APPLICATION_JSON)).nest {
            GET("", handler::findAll)
            GET("/author/{author}", handler::findAllByAuthor)
            GET("/{id}", handler::findById)
            POST("", handler::create)
            PUT("", handler::update)
            DELETE("/{id}", handler::deleteById)
        }
    }
}
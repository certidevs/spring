package com.example.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "books")
data class Book(
    @Id var id: String?,
    @Field("title") var title: String,
    var author: String,
)

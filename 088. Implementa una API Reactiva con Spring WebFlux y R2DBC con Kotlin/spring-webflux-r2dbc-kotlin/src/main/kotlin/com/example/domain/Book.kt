package com.example.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("books")
data class Book(
    @Column("id") @Id var id: Long?,
    @Column("title") var title: String,
    @Column("author") var author: String,
)

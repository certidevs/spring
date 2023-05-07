package com.example.dao;

import com.example.model.Book;
import com.example.model.QBook;
import com.example.repository.BookRepository;
import com.mongodb.QueryBuilder;
import com.querydsl.core.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class BookDAO {

    @Autowired
    private BookRepository repo;

    public List<Book> findByTitleContains(String title){
        var pred = QBook.book.title.containsIgnoreCase(title);
        return StreamSupport.stream(
                        repo.findAll(pred).spliterator(), false)
                .toList();
    }
}

package com.example.repository;

import com.example.model.Book;
import com.example.model.QBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public interface BookRepository extends
        MongoRepository<Book, String>,
        QuerydslPredicateExecutor<Book>

{

    // find by title
    List<Book> findByTitleContains(String title);

    // querydsl methods
    default List<Book> findByTitleContainsDsl(String title){
        var pred = QBook.book.title.containsIgnoreCase(title);

//        var pred = generatePredicate(title);
        return StreamSupport.stream(
                findAll(pred).spliterator(), false)
                .toList();
    }

//    private void generatePredicate(String title){
//
//    }


}

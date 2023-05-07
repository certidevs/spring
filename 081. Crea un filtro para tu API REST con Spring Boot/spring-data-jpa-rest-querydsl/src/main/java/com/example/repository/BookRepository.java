package com.example.repository;

import com.example.model.Book;
import com.example.model.QBook;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends
        JpaRepository<Book, Long>,
        QuerydslPredicateExecutor<Book>,
        QuerydslBinderCustomizer<QBook>
{
    @Override
    default void customize(QuerydslBindings bindings, QBook root){
        System.out.println("Customizing querydsl bindings of BOOK");
        bindings.bind(root.title).first((stringPath, str) -> stringPath.containsIgnoreCase(str));



    }
}
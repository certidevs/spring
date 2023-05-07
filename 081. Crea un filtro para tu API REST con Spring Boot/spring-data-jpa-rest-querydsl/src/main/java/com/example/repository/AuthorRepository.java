package com.example.repository;

import com.example.model.Author;
import com.example.model.QAuthor;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.Optional;

public interface AuthorRepository extends
        JpaRepository<Author, Long>,
        QuerydslPredicateExecutor<Author>,
        QuerydslBinderCustomizer<QAuthor>
{

    @Override
    default void customize(QuerydslBindings bindings, QAuthor author){

        System.out.println("Customizing querydsl bindings of AUTHOR");
        bindings.bind(author.salary).first(NumberExpression::goe);

        bindings.bind(String.class)
                .first((StringPath path, String value) ->
                        path.containsIgnoreCase(value));

        bindings.bind(author.department).all((path, values) -> Optional.of(path.in(values)));

        bindings.bind(author.birth).all((path, values) -> {

            var birthDates = new ArrayList<>(values);
            if (birthDates.size() == 1) {
                return Optional.of(path.goe(birthDates.get(0)));
            } else {
                return Optional.of(path.between(birthDates.get(0), birthDates.get(1)));
            }

        });

    }
}
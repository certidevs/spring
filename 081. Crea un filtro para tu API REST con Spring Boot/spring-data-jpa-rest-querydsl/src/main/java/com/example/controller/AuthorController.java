package com.example.controller;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorRepository repo;

    @GetMapping("/authors")
    public Page<Author> findAll(@QuerydslPredicate(root = Author.class) Predicate predicate,
                                Pageable pageable) {

        return Optional.ofNullable(predicate)
                .map(pred -> repo.findAll(pred, pageable))
                .orElse(repo.findAll(pageable));

    }
}

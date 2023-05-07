package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAll(Model model){
        model.addAttribute("books", this.bookService.findAll());
        return "book-list";
    }

    @GetMapping("/books/{id}")
    public String findById(@PathVariable Long id, Model model){
        Book book = this.bookService.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "book-view";
    }
}

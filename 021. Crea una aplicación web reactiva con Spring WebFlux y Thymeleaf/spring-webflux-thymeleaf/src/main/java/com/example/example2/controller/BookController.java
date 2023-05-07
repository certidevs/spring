package com.example.example2.controller;

import com.example.example2.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {

    @GetMapping("/books")
    public String findAll(Model model){

        RestTemplate client = new RestTemplate();
        List<Book> books = new ArrayList<>();

        books.addAll(List.of(client.getForObject("http://localhost:8080/remote/books", Book[].class)));
        books.addAll(List.of(client.getForObject("http://localhost:8080/remote/books", Book[].class)));
        books.addAll(List.of(client.getForObject("http://localhost:8080/remote/books", Book[].class)));

        model.addAttribute("books", books);
        return "book-list";
    }
}

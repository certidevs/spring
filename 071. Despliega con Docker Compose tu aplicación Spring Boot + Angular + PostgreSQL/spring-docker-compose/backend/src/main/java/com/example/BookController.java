package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @GetMapping
    public List<Book> findAll() {
        System.out.println("findAll");
        return bookRepo.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        System.out.println("findAll");
        return bookRepo.findById(id).orElseThrow();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        bookRepo.findById(id).ifPresent(book -> bookRepo.delete(book));
    }
}

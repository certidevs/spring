package com.example.controller;

import com.example.repository.BookRepository;
import com.example.service.BookService;
import com.example.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    // System under test: SUT
    BookController bookController;
    // Dependencias
    @Mock
    BookService bookService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookService);
    }

    @Test
    void findAll() {
        when(bookService.findAll()).thenReturn(List.of());

        String view = bookController.findAll(model);

        assertEquals("book-list", view);
        verify(model, times(1)).addAttribute(any(), any());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void findById() {
    }
}
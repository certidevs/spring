package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    // System under test: SUT
    BookService bookService;
    // Dependencias
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void findAll() {

        // configurar mock
        when(bookRepository.findAll()).thenReturn(List.of());

        // ejecutar el comportamiento a testear
        List<Book> books = bookService.findAll();

        // comprobaciones de JUnit
        assertNotNull(books);
        assertEquals(0, books.size());
        // verificaciones Mockito
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    void findByIdFound() {
        // configurar mock
        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book(1L, "book1", "description")));

        // ejecutar el comportamiento a testear
        Optional<Book> bookOpt = bookService.findById(1L);

        // comprobaciones de JUnit
        assertNotNull(bookOpt);
        assertTrue(bookOpt.isPresent());
        assertEquals("BOOK1", bookOpt.get().getTitle());
        // verificaciones Mockito
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
    }

    @Test
    void findByIdWrong() {
    }
}
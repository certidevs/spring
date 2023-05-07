package com.example.dao;

import com.example.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> findAllWithCategories();
    List<Book> findAllEager();
}

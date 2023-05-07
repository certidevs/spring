package com.example.cascadeorphan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.author.id = :id")
    void deleteByAuthorIdQuery(Long id);
}
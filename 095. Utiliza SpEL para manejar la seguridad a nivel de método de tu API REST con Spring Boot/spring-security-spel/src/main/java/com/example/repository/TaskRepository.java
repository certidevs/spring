package com.example.repository;

import com.example.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @PostFilter("filterObject.owner == authentication.principal.username")
    List<Task> findByTitleContains(String title);


    @Query("""
        select t from Task t
        where t.title LIKE %:title% and
        t.owner = ?#{authentication.principal.username}
        """)
    List<Task> findByTitleContainsQuery(String title);

}
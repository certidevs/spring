package com.example.repository;

import com.example.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByKeyAndLocale(String key, String locale);
}
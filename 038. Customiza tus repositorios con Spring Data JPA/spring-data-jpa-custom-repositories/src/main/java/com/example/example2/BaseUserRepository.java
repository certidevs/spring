package com.example.example2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean // Previene de ser instanciado directamente, solo pueden instanciarse las que hereden
public interface BaseUserRepository<T extends BaseUser> extends JpaRepository<T, Long> {

    T findByEmail(String email);
}

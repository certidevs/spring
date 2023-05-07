package com.example.repository;


import com.example.model.Account;

public interface AccountRepository {
    Account find(String username);
}


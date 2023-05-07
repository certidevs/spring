package com.example.dto;

import com.example.model.UserEntity;

public record RegisterDTO(String username, String password, String email) {
    public UserEntity toUserEntity() {
        return new UserEntity(username, password, email);
    }
}


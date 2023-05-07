package com.example;

import java.io.Serializable;

public record CustomerDto(String fullName, Type type, Long version) implements Serializable {
}

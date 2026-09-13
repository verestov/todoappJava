package com.mickle.todoapp.dto;

public record CreateUserResponse(
        Long id,
        String username
) {
}

package com.mickle.todoapp.dto;

public record GetAllTasksResponse(
        Long id,
        String title,
        String description,
        java.time.LocalDate created_at
) {
}

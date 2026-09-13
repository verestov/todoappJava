package com.mickle.todoapp.dto;

import com.mickle.todoapp.enums.TaskStatus;

public record GetAllTasksResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        java.time.LocalDate created_at
) {
}

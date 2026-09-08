package com.mickle.todoapp.dto;

public record UpdateTitleResponse(
        Long task_id,
        String title,
        String description
) {
}

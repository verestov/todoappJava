package com.mickle.todoapp.dto;

public record UpdateDescriptionResponse(
        Long task_id,
        String title,
        String description
) {
}

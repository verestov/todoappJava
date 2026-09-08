package com.mickle.todoapp.dto;

public record UpdateDescriptionReq(
        Long task_id,
        String description
) {
}

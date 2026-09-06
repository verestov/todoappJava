package com.mickle.todoapp.dto;

public record CreateTaskReq(
        Long user_id,
        String title,
        String description
) {
}

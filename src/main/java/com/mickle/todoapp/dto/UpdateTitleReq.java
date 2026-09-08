package com.mickle.todoapp.dto;

public record UpdateTitleReq(
        Long task_id,
        String title
) {
}

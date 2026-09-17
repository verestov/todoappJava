package com.mickle.todoapp.dto;

public record GetTaskByIdReq(
        Long user_id,
        Long task_id
) {
}

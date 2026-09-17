package com.mickle.todoapp.dto;

import com.mickle.todoapp.enums.TaskStatus;

public record GetTaskByIdResponse(
        Long task_id,
        String title,
        String description,
        TaskStatus status
) {
}

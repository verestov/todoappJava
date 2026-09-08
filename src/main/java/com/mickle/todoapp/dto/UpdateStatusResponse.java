package com.mickle.todoapp.dto;

import com.mickle.todoapp.enums.TaskStatus;

public record UpdateStatusResponse(
        Long task_id,
        String title,
        TaskStatus status
) {
}

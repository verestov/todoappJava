package com.mickle.todoapp.dto;

import com.mickle.todoapp.enums.TaskStatus;

public record UpdateStatusReq(
        Long task_id,
        TaskStatus status
) {
}
